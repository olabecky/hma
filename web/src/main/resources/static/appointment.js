(() => {
    document.addEventListener("DOMContentLoaded", () => {
        showAppointments('appointment');

        function showAppointments(path) {
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
            var path = 'appointmentSearch?param='+param;
            showAppointments(path);
        })

        document.getElementById('submit-form').addEventListener("click", () => {
            var form = document.getElementById('addAppointment')
            var errorElement = document.getElementById('errorMessage')
            errorElement.innerHTML = '';
            fetch('appointment', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(getFormContentAsObject('addAppointment')),
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
                            showAppointments('appointment');
                        }).catch(function (error) {
                        var errorElement = document.getElementById('errorMessage')
                        showAppointments('appointment');
                        errorElement.innerHTML = `<div>Error occurred: ${error}</div>`;
                    });
                })
                .catch(function (error) {
                    var errorElement = document.getElementById('errorMessage')
                    showAppointments('appointment');
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
            var table = document.getElementById('allAppointments')
            var rows = "<thead><tr><th scope=\"col\">#</th><th scope=\"col\">Firstname</th><th scope=\"col\">Lastname</th><th scope=\"col\">Appointment Description</th><th scope=\"col\">Appointment Date</th></tr></thead>"
            let sn = 1;
            data.forEach(element => {
                rows += `<tr><th scope=\"row\">${sn}</th><td>${element.patient.firstname}</td><td>${element.patient.lastname}</td><td>${element.appointmentDescription}</td><td>${element.appointmentDate}</td></tr>`;
                sn++;
            });
            table.innerHTML = rows;
        }

        function loadPatients(path) {
        fetch(path)
             .then( function(response) {
                 response.json().then(function(data) {
                     if (response.status === 200) {
                         let $select = $('#patientId');
                         $.each(data, function(i, option) {
                             let optionText = option.firstname + ' ' + option.lastname;
                             let optionValue = option.id;
                             $select.append(`<option value="${optionValue}"> ${optionText} </option>`);
                         });
                     } else {
                         var errorElement = document.getElementById('errorMessage')
                         errorElement.innerHTML = `<div>Request failed: ${data.message}</div>`;
                     }
             })
         })
     }
     loadPatients('patientSearch');
    });
})();