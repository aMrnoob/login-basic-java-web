document.addEventListener("DOMContentLoaded", function() {
    
    document.querySelector("input[name='username']").addEventListener("blur", function() {
        checkField("username", this.value);
    });

    document.querySelector("input[name='email']").addEventListener("blur", function() {
        checkField("email", this.value);
    });
	
	document.querySelector("form").addEventListener("submit", function(event) {
	        const emailError = document.getElementById("email-error").innerText;
	        const usernameError = document.getElementById("username-error").innerText;
	        
	        if (emailError || usernameError) {
	            event.preventDefault(); 
	        }
	    });
});

function checkField(field, value) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/LoginProject/check-user", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);

            if (field === "username") {
                document.getElementById("username-error").innerText = response.isUsernameTaken ? "Tên đăng nhập đã được sử dụng." : "";
            } else if (field === "email") {
                document.getElementById("email-error").innerText = response.isEmailTaken ? "Email đã được sử dụng." : "";
            } 
        }
    };

    xhr.send(field + "=" + encodeURIComponent(value));
}
