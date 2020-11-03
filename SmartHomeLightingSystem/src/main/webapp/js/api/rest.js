let SETUP;

function loadCurrentSetup() {

    $.ajax({
        url: "rest/setup",
        type: "GET",
        async: false,
        success: (data) => SETUP = new Setup(data)
    });

    // In case of off system
    if (SETUP.r === 0 &&
        SETUP.g === 0 &&
        SETUP.b === 0 &&
        SETUP.a === 0 &&
        SETUP.anim === 0 &&
        SETUP.preset === 0 &&
        SETUP.doLDR === false) sendSysOff();

    // In case of an uninitialized system
    if (SETUP.r === 0 &&
        SETUP.g === 0 &&
        SETUP.b === 0 &&
        SETUP.a === 0) {

        SETUP.r = 255;
        SETUP.g = 255;
        SETUP.b = 255;
        SETUP.a = 1

    }

    localStorage.setItem("onAmbientLight", SETUP.doLDR);

}

function loadPalette() {

    const __animation = (obj) => `
        <div id="shls-palette-animation-${obj.id}" class="swiper-slide" onclick="{ SETUP.anim = ${obj.id}; sendAnim(); }">
            <div class="mdc-card shadow-2">
                <span class="shls-palette-animation-icon material-icons">${obj.icon}</span>
                <span class="shls-palette-animation-desc">${obj.name}</span>
            </div>
        </div>
    `;
    const __preset = (obj) => `
        <li id="shls-palette-preset-${obj.id}" class="mdc-list-item mdc-form-field" role="radio" data-mdc-auto-init="MDCFormField">
            <span class="mdc-list-item__ripple"></span>
            <label class="mdc-list-item__text" for="shls-palette-preset-${obj.id}-input">
                <span class="mdc-list-item__primary-text">${obj.primary}</span>
                <span class="mdc-list-item__secondary-text">${obj.secondary}</span>
            </label>
            <span class="mdc-list-item__meta">
                <div class="mdc-radio" onclick="{ SETUP.preset = ${obj.id}; sendPreset(); }" data-mdc-auto-init="MDCRadio">
                    <input class="mdc-radio__native-control" type="radio"
                           id="shls-palette-preset-${obj.id}-input"
                           name="shls-palette-preset-radios" value="1"/>
                    <div class="mdc-radio__background">
                        <div class="mdc-radio__outer-circle"></div>
                        <div class="mdc-radio__inner-circle"></div>
                    </div>
                </div>
            </span>
        </li><li class="mdc-list-divider" role="separator"></li>
    `;

    $.get("rest/page/palette", (response) => {

        ANIMSEL.appendSlide(response.animations.map(__animation));
        ANIMSEL.slideTo(SETUP.anim);

        $.each(response.presets, (i, p) => {

            if (i === 0 || i === 1) $(".shls-palette-presets-wrapper > .mdc-list-group:nth-of-type(1) > .mdc-list").append(__preset(p));
            if (i === 2 || i === 3) $(".shls-palette-presets-wrapper > .mdc-list-group:nth-of-type(2) > .mdc-list").append(__preset(p));

        });

        mdc.autoInit();

        document.querySelectorAll("#shls-page-palette .mdc-list-item.mdc-form-field").forEach(el => {

            new mdc.ripple.MDCRipple(el); // For the list item
            el.MDCFormField.input = el.querySelector(".mdc-radio").MDCRadio;

        });

        if (SETUP.preset !== 0) {
            document.querySelector(`#shls-palette-preset-${SETUP.preset} .mdc-radio`).MDCRadio.checked = true
        }

    });

}

function loadLDRData(scope) {

    let data;
    $.get({
        url: `rest/page/ldr/data?scope=${scope}`,
        async: false,
        success: response => { data = response; }
    });
    return data;

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

function sendAnim() {

    $.post(`rest/setup/anim?id=${SETUP.anim}`);
    SETUP.preset = 0;
    document.querySelectorAll("#shls-page-palette .mdc-radio")
        .forEach(el => el.MDCRadio.checked = false);

}

function sendPreset() {

    $.post(`rest/setup/preset?id=${SETUP.preset}`);
    SETUP.anim = 0;
    ANIMSEL.slideTo(SETUP.anim);

}

function sendDoLDR() {

    $.post(`rest/setup/ldr?b=${SETUP.doLDR}`);

}

function sendSysOff() {

    const setupObj = {
        p: SETUP.p, q: SETUP.q,
        r: 0, g: 0, b: 0, a: 0,
        animId: 0, presetId: 0,
        doLDR: false
    }

    $.ajax({
        url: "rest/setup",
        type: "POST",
        data: JSON.stringify(setupObj),
        contentType: "application/json",
        success: () => {

            $(".shls-top-buttons-power").attr("onclick", "sendSysOn()");
            $(".shls-top-buttons-power").toggleClass("shls-top-buttons-power-off shls-top-buttons-power-on");
            $(".shls-page-disabler").show();

        }
    });

}

function sendSysOn() {

    sendRGBA();
    sendAnim();
    $(".shls-top-buttons-power").attr("onclick", "sendSysOff()");
    $(".shls-top-buttons-power").toggleClass("shls-top-buttons-power-on shls-top-buttons-power-off");
    $(".shls-page-disabler").hide();

}