(() => {
    document.addEventListener("DOMContentLoaded", () => {
        showPatients('patient');

        function showPatients(path) {
                fetch(path)
                .then( function(response) {
                    response.json().then(function(data) {
                        if (response.status === 200) {
                            updateTable(data)
                        } else {
                            var errorElement = document.getElementById('errorMessage')
                            errorElement.innerHTML = `<div>Request failed: ${data.message}</div>`;
                        }
                    })
                })
        }

        document.getElementById('search-form').addEventListener("click", () => {
            var form = document.getElementById('searchForm')
            var errorElement = document.getElementById('errorMessage')
            var param = document.getElementById('search').value
            errorElement.innerHTML = '';
            var path = 'patientSearch?param='+param;
            showPatients(path);
        })

        document.getElementById('submit-form').addEventListener("click", () => {
            var form = document.getElementById('addPatient')
            var errorElement = document.getElementById('errorMessage')
            errorElement.innerHTML = '';
            fetch('patient', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(getFormContentAsObject('addPatient')),
            })
                .then(function (response) {
                    response.json()
                        .then(function (data) {
                            if (response.status !== 201) {
                                var errorElement = document.getElementById('errorMessage')
                                errorElement.innerHTML = `<div>Request failed: ${data.message}</div>`;
                            }else{
                                form.reset();
                            }
                            showPatients('patient');
                        }).catch(function (error) {
                        var errorElement = document.getElementById('errorMessage')
                        showPatients('patient');
                        errorElement.innerHTML = `<div>Error occurred: ${error}</div>`;
                    });
                })
                .catch(function (error) {
                    var errorElement = document.getElementById('errorMessage')
                    showPatients('patient');
                    errorElement.innerHTML = `<div>Request failed: ${error}</div>`;
                });
        })

        function getFormContentAsObject(formName) {
            var elements = document.getElementById(formName).elements;
            var obj ={};
            for(var i = 0 ; i < elements.length ; i++){
                var item = elements.item(i);
                obj[item.id] = item.value;
            }
            return obj;
        }

        function updateTable(data) {
            var table = document.getElementById('allPatients')
            var rows = "<thead><tr><th scope=\"col\">#</th><th scope=\"col\">Firstname</th><th scope=\"col\">Lastname</th><th scope=\"col\">Email</th><th scope=\"col\">Phone Number</th><th scope=\"col\">Post Code</th><th scope=\"col\">Address</th></tr></thead>"
            let sn = 1;
            data.forEach(element => {
                rows += `<tr><th scope=\"row\">${sn}</th><td>${element.firstname}</td><td>${element.lastname}</td><td>${element.email}</td><td>${element.phoneNumber}</td><td>${element.postCode}</td><td>${element.address}</td></tr>`;
                sn++;
            });
            table.innerHTML = rows;
        }
    });
})();