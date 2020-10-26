let P, Q, COLOR;

function loadCurrentSetup() {

    // Simulate receiving
    P = localStorage.getItem("p");
    Q = localStorage.getItem("q");
    COLOR = localStorage.getItem("color");

    if (COLOR === null) COLOR = "#FFFFFF";
    handleColorChange(COLOR);

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

function handleColorChange(hexColor) {

    COLOR = hexColor;
    const rgb = parseInt(COLOR.substring(1), 16); // convert rrggbb to decimal
    const r = (rgb >> 16) & 0xff; // extract red
    const g = (rgb >>  8) & 0xff; // extract green
    const b = (rgb >>  0) & 0xff; // extract blue
    const luma = 0.2126 * r + 0.7152 * g + 0.0722 * b; // per ITU-R BT.709

    if (luma < 200) $ROOT.css("--mdc-theme-secondary", COLOR);
    else $ROOT.css("--mdc-theme-secondary", "var(--mdc-theme-primary)");

    $(".shls-pallate-selection-text").text(hexColor.toUpperCase());

    // Simulate sending
    localStorage.setItem("color", COLOR);

}

function handleSizeChange() {

    const p = $("input#shls-setup-display-p-input").val();
    const q = $("input#shls-setup-display-q-input").val();

    if (!isNaN(parseInt(p)) && !isNaN(parseInt(q))) {

        // Simulate sending
        localStorage.setItem("p", p);
        localStorage.setItem("q", q);

        console.log(`p = ${p} and q = ${q}`);
        startApp();

    } else console.log("Enter a number, you cunt!");

}