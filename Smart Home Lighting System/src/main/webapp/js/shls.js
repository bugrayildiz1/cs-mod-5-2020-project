const $ROOT = $(":root");
let WHEEL, BRSLIDER, ANIMSEL,
    LDRGRAPH, LDRSWITCH,
    DARKSWITCH, COLORSWITCH;

$(document).ready(function() {

    mdc.autoInit(); // Start MDC and set up components

    // Initialize loading bar
    const linearProgress = document.querySelector(".shls-welcome-header-loading > .mdc-linear-progress").MDCLinearProgress;
    linearProgress.determinate = false;
    linearProgress.open();

    // Set Palette Tab and Animations Tab
    document.querySelector(".shls-tabbar").MDCTabBar.activateTab(0);
    document.querySelector(".shls-palette-functions-tabbar").MDCTabBar.activateTab(0);

});

function startApp() {

    if (WHEEL === undefined) WHEEL = new ColorWheel(".shls-palette-iro-colorwheel", 250);

    if (BRSLIDER === undefined) {
        BRSLIDER = new mdc.slider.MDCSlider(document.querySelector('.mdc-slider'));
        BRSLIDER.value = SETUP.a * 100;
        BRSLIDER.root.addEventListener("MDCSlider:change", (event) => {
            SETUP.a = event.detail.value / 100;
            sendRGBA();
        });
    }

    if (LDRGRAPH === undefined) LDRGRAPH = new LDRGraph("#shls-ldr-chart");

    if (LDRSWITCH === undefined) LDRSWITCH = new SHLSSwitch({
        id: "onAmbientLight",
        target: ".shls-ldrdata-doldr .mdc-switch",
        default: false,
        action: () => {
            if (LDRSWITCH.state) {
                SETUP.doLDR = true;
                sendDoLDR();
                $("#shls-page-palette .shls-page-disabler").show();
            } else {
                SETUP.doLDR = false;
                sendRGBA(); // will set doLDR to false in BE
                sendAnim();
                $("#shls-page-palette .shls-page-disabler").hide();
            }
        }
    });

    if (DARKSWITCH === undefined) DARKSWITCH = new SHLSSwitch({
        id: "darkTheme",
        target: ".shls-settings-darktheme .mdc-switch",
        default: false,
        action: () => {
            if (DARKSWITCH.state) {
                $ROOT.attr("data-shls-theme", "dark");
                LDRGRAPH.obj.options.scales.xAxes[0].ticks.fontColor = $ROOT.css("--shls-theme-text-secondary");
                LDRGRAPH.obj.update();
            } else {
                $ROOT.attr("data-shls-theme", "light");
                LDRGRAPH.obj.options.scales.xAxes[0].ticks.fontColor = $ROOT.css("--shls-theme-text-primary");
                LDRGRAPH.obj.update();
            }
        }
    });

    if (COLORSWITCH === undefined) COLORSWITCH = new SHLSSwitch({
        id: "changeThemeOnColorUpdate",
        target: ".shls-settings-updateoncolor .mdc-switch",
        default: true,
        action: () => onColorChange(SETUP.r, SETUP.g, SETUP.b)
    });

    $(".shls-settings-profile-dialog .mdc-dialog__content").html(`
        <p>Name: ${GPROFILE.getName()}</p>
        <p>Email: ${GPROFILE.getEmail()}</p>
    `);

    DARKSWITCH.action();
    COLORSWITCH.action();
    if (LDRSWITCH.state) LDRSWITCH.action();
    onResize();
    $(window).resize(onResize);

    $("#shls-backdrop").show();
    $("#shls-welcome").fadeOut(1000, () => {

        $("#shls-app").fadeIn();

        openPalette();
        openPaletteAnimations();

        if (ANIMSEL === undefined) {
            ANIMSEL = new AnimationSelector(".swiper-container");
            loadPalette();
        }

    });

}



function onResize() {

    let w = $(window).width();

    // Global Gutter
    if (w < 840) $ROOT.css("--shls-mdc-gutter", "16px");
    else $ROOT.css("--shls-mdc-gutter", "24px");

    // Color Wheel
    let colorWheelWidth;
    const $wheelWrapper = $(".shls-palette-iro-colorwheel");

    if (w < 600) colorWheelWidth = 250;
    else if (w >= 600 && w < 840) colorWheelWidth = 280;
    else if (w > 840) colorWheelWidth = 300;

    if ($wheelWrapper.width() != colorWheelWidth) {
        $wheelWrapper.width(colorWheelWidth)
        WHEEL.obj.resize(colorWheelWidth);
    }

    // Chart
    const tickLim = LDRGRAPH.obj.options.scales.xAxes[0].ticks.maxTicksLimit;

    if (w < 840 && tickLim === LDRGRAPH.sizes.desktop[LDRGRAPH.scope]()) LDRGRAPH.resize(true);
    else if (w >= 840 && tickLim === LDRGRAPH.sizes.mobile[LDRGRAPH.scope]()) LDRGRAPH.resize(false);

}

function onColorChange(r, g, b) {

    SETUP.r = r;
    SETUP.g = g;
    SETUP.b = b;

    if (COLORSWITCH.state) {

        const luma = 0.2126 * r + 0.7152 * g + 0.0722 * b; // per ITU-R BT.709

        if (luma < 200) $ROOT.css("--mdc-theme-secondary", `rgb(${r}, ${g}, ${b})`);
        else $ROOT.css("--mdc-theme-secondary", "var(--mdc-theme-primary)");

        // Make HEX for displaying
        r = Number(r).toString(16); if (r.length < 2) r = "0" + r;
        g = Number(g).toString(16); if (g.length < 2) g = "0" + g;
        b = Number(b).toString(16); if (b.length < 2) b = "0" + b;
        $(".shls-palette-selection-text").text(("#" + r + g + b).toUpperCase());

    } else $ROOT.css("--mdc-theme-secondary", "var(--mdc-theme-primary)");

}

function renderTitle(suffix) {

    document.title = "SLHS | " + suffix;

}



function openWelcome() {

    const $target = $("#shls-welcome");
    $target.siblings().hide();
    $target.show();
    $target.find(".shls-welcome-header-device").show();

}

function openApp() {

    const $target = $("#shls-app, #shls-backdrop");
    $target.siblings().hide();
    $target.show();

}

function openSignIn() {

    renderTitle("Sign In");
    const target = $("#shls-page-signin");
    target.siblings().not(".shls-welcome-header-device").hide();
    target.show();

}

function openSetUp() {

    renderTitle("Set Up");
    $(".shls-setup-title > h2").text("Welcome " + GPROFILE.getGivenName());
    SETUP.p = 0;
    SETUP.q = 0;

    const $target = $("#shls-page-setup");
    $target.siblings().not(".shls-welcome-header-device").hide();
    $target.show();

}

function openPalette() {

    renderTitle("Palette");
    const $target = $("#shls-page-palette");
    $target.siblings().not(".shls-tabbar-wrapper").hide();
    $target.show();

    BRSLIDER.layout();

}

function openExtend() {

    renderTitle("Extend");
    const $target = $("#shls-page-extend");
    $target.siblings().not(".shls-tabbar-wrapper").hide();
    $target.show();

}

function openLdr() {

    renderTitle("LDR Data");
    const $target = $("#shls-page-ldr");
    $target.siblings().not(".shls-tabbar-wrapper").hide();
    $target.show();

}

function openSettings() {

    renderTitle("Settings");
    const $target = $("#shls-page-settings");
    $target.siblings().not(".shls-tabbar-wrapper").hide();
    $target.show();

}

function openPaletteAnimations() {

    const $target = $(".shls-palette-animations-wrapper") ;
    $target.siblings().not(".shls-palette-functions-tabbar-wrapper").hide();
    $target.show();

}

function openPalettePresets() {

    const $target = $(".shls-palette-presets-wrapper");
    $target.siblings().not(".shls-palette-functions-tabbar-wrapper").hide();
    $target.show();

}
