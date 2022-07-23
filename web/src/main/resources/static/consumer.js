(() => {
    document.addEventListener("DOMContentLoaded", () => {

        showHeroes();

        function showHeroes() {
            fetch('hero')
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

        document.getElementById('submit-form').addEventListener("click", () => {
            var form = document.getElementById('addHero')
            var errorElement = document.getElementById('errorMessage')
            errorElement.innerHTML = '';
            fetch('hero', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(getFormContentAsObject('addHero')),
            })
                .then(function (response) {
                    response.json()
                        .then(function (data) {
                            if (response.status !== 200) {
                                var errorElement = document.getElementById('errorMessage')
                                errorElement.innerHTML = `<div>Request failed: ${data.message}</div>`;
                            }
                            showHeroes();
                        }).catch(function (error) {
                        var errorElement = document.getElementById('errorMessage')
                        showHeroes();
                        errorElement.innerHTML = `<div>Error occurred: ${error}</div>`;
                    });
                })
                .catch(function (error) {
                    var errorElement = document.getElementById('errorMessage')
                    showHeroes();
                    errorElement.innerHTML = `<div>Request failed: ${error}</div>`;
                });
        })

        function getFormContentAsObject(formName) {
            var elements = document.getElementById(formName).elements;
            var obj ={};
            for(var i = 0 ; i < elements.length ; i++){
                var item = elements.item(i);
                obj[item.name] = item.value;
            }
            return obj;
        }

        function updateTable(data) {
            var table = document.getElementById('allHeroes')
            var rows = "<th>Name</th><th>Alias</th>"
            data.forEach(element => {
                rows += `<tr><td>${element.name}</td><td>${element.alias}</td></tr>`;
            });
            table.innerHTML = rows;
        }
    });
})();