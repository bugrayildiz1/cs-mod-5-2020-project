const $ROOT = $(":root");
let WHEEL, ANIMSEL, LDRGRAPH, BRSLIDER;

$(document).ready(function() {

    // Start MDC and set up components
    mdc.autoInit();

    const linearProgress = document.querySelector(".shls-welcome-header-loading > .mdc-linear-progress").MDCLinearProgress;
    linearProgress.determinate = false;
    linearProgress.open();

    document.querySelector(".shls-tabbar").MDCTabBar.activateTab(0);
    document.querySelector(".shls-pallate-functions-tabbar").MDCTabBar.activateTab(0);

    document.querySelector(".mdc-chip--selected").MDCChip

});

function startApp() {

    loadCurrentSetup();
    $("#shls-backdrop").show();

    $("#shls-welcome").fadeOut(1000, () => {

        $("#shls-app").fadeIn();

        openPallate();
        openPallateAnimations();
        WHEEL = new ColorWheel(".shls-pallate-iro-colorwheel", 250);
        BRSLIDER = new mdc.slider.MDCSlider(document.querySelector('.mdc-slider'));
        ANIMSEL = new AnimationSelector(".swiper-container");
        LDRGRAPH = new LDRGraph("#shls-ldr-chart");

        onResize();
        $(window).resize(onResize);

    });

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



function onResize() {

    let w = $(window).width();

    // Global Gutter
    if (w < 840) $ROOT.css("--shls-mdc-gutter", "16px");
    else $ROOT.css("--shls-mdc-gutter", "24px");

    // Color Wheel
    let colorWheelWidth;

    if (w < 600) colorWheelWidth = 250;
    else if (w >= 600 && w < 840) colorWheelWidth = 280;
    else if (w > 840) colorWheelWidth = 300;

    if ($(".shls-pallate-iro-colorwheel").width() != colorWheelWidth)
        WHEEL = new ColorWheel(".shls-pallate-iro-colorwheel", colorWheelWidth);

    // Chart
    if (w < 840 && !LDRGRAPH.mobile) {
        LDRGRAPH.mobile = true;
        LDRGRAPH.update();
    } else if (w >= 840 && LDRGRAPH.mobile) {
        LDRGRAPH.mobile = false;
        LDRGRAPH.update();
    }

}

function renderTitle(suffix) {

    document.title = "SLHS | " + suffix;

}