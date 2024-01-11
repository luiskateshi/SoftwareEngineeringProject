class Signup {
    constructor(form, fields) {
        this.form = form;
        this.fields = fields;
        this.validateOnSubmit();
    }

    validateOnSubmit() {
        let self = this;

        this.form.addEventListener("submit", (e) => {
            e.preventDefault();
            let error = 0;

            self.fields.forEach((field) => {
                const input = document.querySelector(`#${field}`);
                if (self.validateFields(input) === false) {
                    error++;
                }
            });

            if (error === 0) {
                // Perform signup logic here

                const name = document.querySelector('#name').value;
                const email = document.querySelector('#email').value;
                const username = document.querySelector('#username').value;
                const password = document.querySelector('#password').value;

                var data = {
                    name: name,
                    email: email,
                    username: username,
                    password: password,
                };

                fetch('http://localhost:8080/auth/register', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(data),
                })
                    .then(response => response.json())
                    .then(data => {
                        // if (data.success) {
                        //     // Signup successful, you can redirect or perform any other action
                        //     // For example, you can redirect to the login page
                        //     window.location.href = "/login";
                        // } else {
                        //     document.querySelector(".error-message-all").style.display = "block";
                        //     document.querySelector(".error-message-all").innerText = data.message;
                        // }
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

const form = document.querySelector(".signupForm");
if (form) {
    const fields = ["name", "email", "username", "password"];
    const validator = new Signup(form, fields);
}
