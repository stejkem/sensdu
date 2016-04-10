    function validateForm() {
        var word = document.forms["mainInput"]["word"].value;
        var vector = document.forms["mainInput"]["vector"].value;
            if (!word) {
                document.getElementById("wordInput").className = "form-group has-error";
                document.getElementById("errorInWord").innerHTML = "Please, fill in the word";
                return false;
            }

            if(!vector) {
                document.getElementById("vectorInput").className = "form-group has-error";
                document.getElementById("errorInVector").innerHTML = "Please, fill in the vector of translation";
                return false;
            }
    }

    function resetHandler() {
        document.getElementById("wordInput").className = "form-group";
        document.getElementById("vectorInput").className = "form-group";
        document.getElementById("errorInWord").innerHTML = "";
        document.getElementById("errorInVector").innerHTML = "";
    }