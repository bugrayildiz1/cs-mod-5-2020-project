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

    const __animation = (obj) => `
        <div id="shls-pallate-animation-${obj.id}" class="swiper-slide">
            <div class="mdc-card shadow-2">
                <span class="shls-pallate-animation-icon material-icons">${obj.icon}</span>
                <span class="shls-pallate-animation-desc">${obj.name}</span>
            </div>
        </div>
    `;
    const __preset = (obj) => `
        <li id="shls-pallate-preset-${obj.id}" class="mdc-list-item" role="radio" aria-checked="false" data-mdc-auto-init="MDCRipple">
            <span class="mdc-list-item__ripple"></span>
            <span class="mdc-list-item__graphic">
                <div class="mdc-radio">
                    <input class="mdc-radio__native-control"
                           type="radio"
                           id="shls-pallate-preset-${obj.id}-input"
                           name="shls-pallate-preset-radios"
                           value="1"/>
                    <div class="mdc-radio__background">
                        <div class="mdc-radio__outer-circle"></div>
                        <div class="mdc-radio__inner-circle"></div>
                    </div>
                </div>
            </span>
            <label class="mdc-list-item__text" for="shls-pallate-preset-${obj.id}-input">
                <span class="mdc-list-item__primary-text">${obj.primary}</span>
                <span class="mdc-list-item__secondary-text">${obj.secondary}</span>
            </label>
        </li>
    `;
    const __presetSeparator = `
        <li class="mdc-list-divider" role="separator"></li>
    `;
    const __presetSeparatorInset = `
        <li class="mdc-list-divider mdc-list-divider--inset" role="separator"></li>
    `;

    $.get("rest/page/palette", (data) => {

        ANIMSEL.appendSlide(data.animations.map(__animation));

        const $rainbow = $(".shls-pallate-presets-wrapper > .mdc-list-group:nth-of-type(1) > .mdc-list");
        const $other = $(".shls-pallate-presets-wrapper > .mdc-list-group:nth-of-type(2) > .mdc-list");

        $.each(data.presets, (i, p) => {

            switch (p.id) {
                case 1:
                    $rainbow.append(__preset(p));
                    $rainbow.append(__presetSeparatorInset);
                    break;
                case 2:
                    $rainbow.append(__preset(p));
                    $rainbow.append(__presetSeparator);
                    break;
                case 3:
                    $other.append(__preset(p));
                    $other.append(__presetSeparatorInset);
                    break;
                case 4:
                    $other.append(__preset(p));
                    $other.append(__presetSeparator);
                    break;
            }

        });

    });

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
