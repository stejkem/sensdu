    function validateForm() {
        var word = document.forms["mainInput"]["word"].value;
            if (!word) {
                document.getElementById("wordInput").className = "form-group has-error";
                document.getElementById("errorInWord").innerHTML = "Please, fill in the word";
                return false;
            }
    }

    function resetHandler() {
        document.getElementById("wordInput").className = "form-group";
        document.getElementById("errorInWord").innerHTML = "";
    }