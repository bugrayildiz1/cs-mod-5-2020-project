let AUTH, GUSER, GPROFILE;

function authInit() {

    gapi.load('auth2', () => {

        gapi.auth2.init({
            client_id: "127840733535-1pnq9jfs5v0f2b1tbbpjbk0dt864cso3.apps.googleusercontent.com",
            ux_mode: "redirect"
        }).then(() => {
            if (!gapi.auth2.getAuthInstance().isSignedIn.get()) {
                openWelcome();
                openSignIn();
            }
        });

        AUTH = gapi.auth2.getAuthInstance();

    });

    gapi.signin2.render("my-signin2", {
        height: 50,
        longtitle: true,
        theme: "dark",
        onsuccess: onSignIn
    });

}

function onSignIn() {

    GUSER = AUTH.currentUser.get();
    GPROFILE = GUSER.getBasicProfile();

    $.ajaxSetup({
        beforeSend: (xhr) => {
            xhr.setRequestHeader("Authorization", "Bearer " + GUSER.getAuthResponse().id_token);
        }
    });

    loadCurrentSetup();

    if (SETUP.p === 0 || SETUP.q === 0) {
        openWelcome();
        openSetUp();
    } else startApp();

}
