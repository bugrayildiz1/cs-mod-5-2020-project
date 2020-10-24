const $ROOT = $(":root");
let COLOR;

$(document).ready(function() {

    // Start MDC and set up components
    mdc.autoInit();

    const linearProgress = document.querySelector(".shls-welcome-header-loading > .mdc-linear-progress").MDCLinearProgress;
    linearProgress.determinate = false;
    linearProgress.open();

    document.querySelector(".shls-tabbar").MDCTabBar.activateTab(0);
    document.querySelector(".shls-pallate-functions-tabbar").MDCTabBar.activateTab(0);

});

function startApp() {

    const color = localStorage.getItem("color"); // simulating BE
    color === null ? COLOR = "#FFFFFF" : COLOR = color;
    updatePageTheme(COLOR);
    $("#shls-backdrop").show();

    $("#shls-welcome").fadeOut(1000, () => {

        $("#shls-app").fadeIn();

        openPallate();
        openPallateAnimations();
        onResize();
        $(window).resize(onResize);
        renderSwiper();
        new mdc.slider.MDCSlider(document.querySelector('.mdc-slider'));

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

function renderColorWheel(size) {

    const $target = $(".shls-pallate-iro-colorwheel");
    $target.empty().width(size + "px");

    // Initiate iro.js color wheel
    const wheel = new iro.ColorPicker(".shls-pallate-iro-colorwheel", {
        width: size,
        color: COLOR,
        layout: [ { component: iro.ui.Wheel } ],
        handleSvg: "#shls-pallate-iro-colorwheel-handle",
        borderColor: "#fff",
        borderWidth: 2
    });

    // Add shadow to wheel
    const defs = $target.find("defs");
    const shadowId = "shls-pallate-iro-colorwheel-shadow";

    defs.html(
        `<filter id="${shadowId}">
             <feDropShadow dx="0" dy="5" stdDeviation="8"/>
         </filter>`
        + defs.html()
    );

    $target.find(".IroWheelBorder").attr("filter", `url(#${shadowId})`);

    // Add listener and change theme accordingly
    wheel.on('color:change', (color) => updatePageTheme(color.hexString));

}

function updatePageTheme(hexColor) {

    COLOR = hexColor;
    const rgb = parseInt(COLOR.substring(1), 16);   // convert rrggbb to decimal
    const r = (rgb >> 16) & 0xff;  // extract red
    const g = (rgb >>  8) & 0xff;  // extract green
    const b = (rgb >>  0) & 0xff;  // extract blue
    const luma = 0.2126 * r + 0.7152 * g + 0.0722 * b; // per ITU-R BT.709

    if (luma < 200) $ROOT.css("--mdc-theme-secondary", COLOR);
    else $ROOT.css("--mdc-theme-secondary", "var(--mdc-theme-primary)");

    $(".shls-pallate-selection-text").text(hexColor.toUpperCase());

    // SEND TO BE
    localStorage.setItem("color", COLOR);

}

function renderSwiper() {

    new Swiper(".swiper-container", {
        slidesPerView: 3,
        centeredSlides: true,
        slideToClickedSlide: true,
        pagination: {
            el: ".swiper-pagination",
            dynamicBullets: true,
            clickable: true
        },
        breakpoints: {
            1000: { slidesPerView: 7 },
            840:  { slidesPerView: 6 },
            700:  { slidesPerView: 5 },
            600:  { slidesPerView: 4 }
        }
    });

}

function renderTitle(suffix) {
    document.title = "SLHS | " + suffix;
}

function onResize() {

    let w = $(window).width();
    let colorWheelWidth;

    if (w < 600) colorWheelWidth = 250;
    else if (w >= 600 && w < 900) colorWheelWidth = 280;
    else if (w > 900) colorWheelWidth = 300;

    if ($(".shls-pallate-iro-colorwheel").width() != colorWheelWidth) renderColorWheel(colorWheelWidth);

    if (w < 840) $ROOT.css("--shls-mdc-gutter", "16px");
    else $ROOT.css("--shls-mdc-gutter", "24px");

}

function setPQ() {

    const p = $("input#shls-setup-display-p-input").val();
    const q = $("input#shls-setup-display-q-input").val();

    if (!isNaN(parseInt(p)) && !isNaN(parseInt(q))) {

        localStorage.setItem("p", p);
        localStorage.setItem("q", q);
        console.log(`p = ${p} and q = ${q}`);
        startApp();

    } else console.log("Enter a number, you cunt!")


}
