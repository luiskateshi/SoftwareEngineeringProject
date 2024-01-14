
  function  logOut() {
        localStorage.removeItem("username");
        localStorage.removeItem("token");
        // localStorage.removeItem("auth");
        window.location.replace("/");
    }

