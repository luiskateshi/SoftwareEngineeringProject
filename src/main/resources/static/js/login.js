class Login {
    constructor(form, fields) {
        this.form = form;
        this.fields = fields;
        this.validateonSubmit();
    }

    validateonSubmit() {
        let self = this;

        this.form.addEventListener("submit", (e) => {
            e.preventDefault();
            var error = 0;
            self.fields.forEach((field) => {
                const input = document.querySelector(`#${field}`);
                if (self.validateFields(input) === false) {
                    error++;
                }
            });
            if (error === 0) {
                //do login api here

                // const username = document.querySelector('#username').value;
                // const password = document.querySelector('#password').value;

                var data = {
                    username: document.querySelector('#username').value,
                    password: document.querySelector('#password').value,
                };

                fetch('http://localhost:8080/auth/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(data),
                })
                    .then(response => response.json())
                    .then(data => {
                        if (data.success){
                            localStorage.setItem("username", data.user.username);
                            localStorage.setItem("token", data.token);
                            // localStorage.setItem("auth", 1);
                            this.form.submit();

                        }else{
                            document.querySelector(".error-message-all").style.display = "block";
                            document.querySelector(".error-message-all").innerText = "Your username or password is incorrect";
                        }
                    })
                    .catch(error => console.error('Error:', error));
            }
        });
    }

    validateFields(field) {
        if (field.value.trim() === "") {
            this.setStatus(
                field,
                `${field.previousElementSibling.innerText} cannot be blank`,
                "error"
            );
            return false;
        } else {
            if (field.type === "password") {
                if (field.value.length < 8) {
                    this.setStatus(
                        field,
                        `${field.previousElementSibling.innerText} must be at least 8 characters`,
                        "error"
                    );
                    return false;
                } else {
                    this.setStatus(field, null, "success");
                    return true;
                }
            } else {
                this.setStatus(field, null, "success");
                return true;
            }
        }
    }

    setStatus(field, message, status) {
        const errorMessage = field.parentElement.querySelector(".error-message");

        if (status === "success") {
            if (errorMessage) {
                errorMessage.innerText = "";
            }
            field.classList.remove("input-error");
        }

        if (status === "error") {
            errorMessage.innerText = message;
            field.classList.add("input-error");
        }
    }
}

const form = document.querySelector(".loginForm");
if (form) {
    const fields = ["username", "password"];
    const validator = new Login(form, fields);
}
