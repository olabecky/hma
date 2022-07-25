(() => {
    document.addEventListener("DOMContentLoaded", () => {

        showUsers('user');

        function showUsers(path) {
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
            var path = 'userSearch?param='+param;
            showUsers(path);
        })

        document.getElementById('submit-form').addEventListener("click", () => {
            var form = document.getElementById('addUser')
            var errorElement = document.getElementById('errorMessage')
            errorElement.innerHTML = '';
            fetch('user', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(getFormContentAsObject('addRole')),
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
                            showUsers('user');
                        }).catch(function (error) {
                        var errorElement = document.getElementById('errorMessage')
                        showHeroes();
                        errorElement.innerHTML = `<div>Error occurred: ${error}</div>`;
                    });
                })
                .catch(function (error) {
                    var errorElement = document.getElementById('errorMessage')
                    showUsers('user');
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
            var table = document.getElementById('allRoles')
            var rows = "<thead><tr><th scope=\"col\">#</th><th scope=\"col\">Username</th><th scope=\"col\">Role</th></tr></thead>"
            let sn = 1;
            data.forEach(element => {
                rows += `<tr><th scope=\"row\">${sn}</th><td>${element.username}</td><td>${element.role}</td></tr>`;
                sn++;
            });
            table.innerHTML = rows;
        }
    });
})();