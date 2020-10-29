const $ROOT = $(":root");
let WHEEL, ANIMSEL, LDRGRAPH, BRSLIDER;

$(document).ready(function() {

    mdc.autoInit(); // Start MDC and set up components

    const linearProgress = document.querySelector(".shls-welcome-header-loading > .mdc-linear-progress").MDCLinearProgress;
    linearProgress.determinate = false;
    linearProgress.open();

    document.querySelector(".shls-tabbar").MDCTabBar.activateTab(0);
    document.querySelector(".shls-pallate-functions-tabbar").MDCTabBar.activateTab(0);

});

function startApp() {

    $("#shls-backdrop").show();
    $("#shls-welcome").fadeOut(1000, () => {

        $("#shls-app").fadeIn();

        openPallate();
        openPallateAnimations();

        if (WHEEL === undefined) WHEEL = new ColorWheel(".shls-pallate-iro-colorwheel", 250);
        if (BRSLIDER === undefined) {
            BRSLIDER = new mdc.slider.MDCSlider(document.querySelector('.mdc-slider'));
            BRSLIDER.value = SETUP.a * 100;
            BRSLIDER.root.addEventListener("MDCSlider:change", (event) => {
                SETUP.a = event.detail.value / 100;
                sendRGBA();
            });
        }
        if (ANIMSEL === undefined) {
            ANIMSEL = new AnimationSelector(".swiper-container");
            loadPallate();
        }
        if (LDRGRAPH === undefined) LDRGRAPH = new LDRGraph("#shls-ldr-chart");

        onResize();
        $(window).resize(onResize);

    });

}



function onResize() {

    let w = $(window).width();

    // Global Gutter
    if (w < 840) $ROOT.css("--shls-mdc-gutter", "16px");
    else $ROOT.css("--shls-mdc-gutter", "24px");

    // Color Wheel
    let colorWheelWidth;
    const $wheelWrapper = $(".shls-pallate-iro-colorwheel");

    if (w < 600) colorWheelWidth = 250;
    else if (w >= 600 && w < 840) colorWheelWidth = 280;
    else if (w > 840) colorWheelWidth = 300;

    if ($wheelWrapper.width() != colorWheelWidth) {
        $wheelWrapper.width(colorWheelWidth)
        WHEEL.obj.resize(colorWheelWidth);
    }

    // Chart
    if (w < 840 && !LDRGRAPH.mobile) {
        LDRGRAPH.mobile = true;
        LDRGRAPH.update();
    } else if (w >= 840 && LDRGRAPH.mobile) {
        LDRGRAPH.mobile = false;
        LDRGRAPH.update();
    }

}

function onColorChange(r, g, b) {

    SETUP.r = r;
    SETUP.g = g;
    SETUP.b = b;

    const luma = 0.2126 * r + 0.7152 * g + 0.0722 * b; // per ITU-R BT.709

    if (luma < 200) $ROOT.css("--mdc-theme-secondary", `rgb(${r}, ${g}, ${b})`);
    else $ROOT.css("--mdc-theme-secondary", "var(--mdc-theme-primary)");

    r = Number(r).toString(16); if (r.length < 2) r = "0" + r;
    g = Number(g).toString(16); if (g.length < 2) g = "0" + g;
    b = Number(b).toString(16); if (b.length < 2) b = "0" + b;
    $(".shls-pallate-selection-text").text(("#" + r + g + b).toUpperCase());

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

function openPallate() {

    renderTitle("Pallate");
    const $target = $("#shls-page-pallate");
    $target.siblings().not(".shls-tabbar-wrapper").hide();
    $target.show();

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

function openPallateAnimations() {

    const $target = $(".shls-pallate-animations-wrapper") ;
    $target.siblings().not(".shls-pallate-functions-tabbar-wrapper").hide();
    $target.show();

}

function openPallatePresets() {

    const $target = $(".shls-pallate-presets-wrapper");
    $target.siblings().not(".shls-pallate-functions-tabbar-wrapper").hide();
    $target.show();

}
