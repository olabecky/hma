(() => {
    document.addEventListener("DOMContentLoaded", () => {

        document.getElementById('submit-form').addEventListener("click", () => {
            var form = document.getElementById('loginUser')
            var errorElement = document.getElementById('errorMessage')
            errorElement.innerHTML = '';
            fetch('authenticateUser', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(getFormContentAsObject('loginUser')),
            }).then(function (response) {
                if (response.status !== 200) {
                    response.json().then(function (data) {
                        var errorElement = document.getElementById('errorMessage')
                        errorElement.innerHTML = `<div>Request failed: ${data.message}</div>`;
                    }).catch(function (error) {
                        var errorElement = document.getElementById('errorMessage')
                        errorElement.innerHTML = `<div>Error occurred: ${error}</div>`;
                    });
                }  else{
                    location.href ='/';
                }
                }).catch(function (error) {
                    var errorElement = document.getElementById('errorMessage')
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

    });
})();