let SETUP;

function loadCurrentSetup() {

    $.ajax({
        url: "rest/setup",
        type: "GET",
        async: false,
        success: (data) => SETUP = new Setup(data)
    });

    if (SETUP.r === 0 && SETUP.g === 0 && SETUP.b === 0) {
        SETUP.r = 255;
        SETUP.g = 255;
        SETUP.b = 255;
    }

    if (SETUP.a === 0) SETUP.a = 1;

    onColorChange(SETUP.r, SETUP.g, SETUP.b);

}

function loadPallate() {



}

function loadLDRData(mobile, scope) {

    // Simulate from BE
    if (mobile) switch (scope) {

        case "def":
            return {
                labels: ['', '10:00', '12:00', '14:00', '16:00', '18:00', ''],
                data: [47, 56, 74, 73, 84, 63, 48]
            }
        case "day": break;
        case "week": break;
        case "month": break;

    } else switch (scope) {

        case "def":
            return {
                labels: ['', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', ''],
                data: [47, 56, 64, 74, 79, 73, 75, 84, 76, 63, 48, 28]
            }
        case "day": break;
        case "week": break;
        case "month": break;

    }

}

function sendPQ() {

    const p = $("input#shls-setup-display-p-input").val();
    const q = $("input#shls-setup-display-q-input").val();

    if (!isNaN(parseInt(p)) && !isNaN(parseInt(q))) {

        SETUP.p = p;
        SETUP.q = q;
        $.post(`rest/setup/pq?q=${SETUP.p}&p=${SETUP.q}`);
        startApp();

    } else console.log("Enter a number, you cunt!");

}

function sendRGBA() {

    $.post(`rest/setup/rgba?r=${SETUP.r}&g=${SETUP.g}&b=${SETUP.b}&a=${SETUP.a}`);

}
