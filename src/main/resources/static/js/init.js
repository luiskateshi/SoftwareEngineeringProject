authentication = new Auth();

document.querySelector(".logout").addEventListener("click", (e) => {
    authentication.logOut();
});