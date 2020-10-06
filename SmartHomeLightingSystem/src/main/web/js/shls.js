$(document).ready(function() {

    // Start MDC and set up components
    mdc.autoInit();
    document.querySelector(".shls-tabbar").MDCTabBar.activateTab(0);
    document.querySelector(".shls-pallate-functions-tabbar").MDCTabBar.activateTab(0);

    // Render screen and open first page
    renderColorWheel();
    openPallate();
    openPallateAnimations();

    // Only when screen is ready
    renderSwiper();
    const slider = new mdc.slider.MDCSlider(document.querySelector('.mdc-slider')); //must be after openPallate

});

function openPallate() {

    $("#shls-page-pallate").siblings().not(".shls-tabbar-wrapper").hide();
    $("#shls-page-pallate").show();

}

function openExtend() {

    $("#shls-page-extend").siblings().not(".shls-tabbar-wrapper").hide();
    $("#shls-page-extend").show();

}

function openLdr() {

    $("#shls-page-ldr").siblings().not(".shls-tabbar-wrapper").hide();
    $("#shls-page-ldr").show();

}

function openSettings() {

    $("#shls-page-settings").siblings().not(".shls-tabbar-wrapper").hide();
    $("#shls-page-settings").show();

}

function openPallateAnimations() {

    $(".shls-pallate-animations-wrapper").siblings().not(".shls-pallate-functions-tabbar-wrapper").hide();
    $(".shls-pallate-animations-wrapper").show();

}

function openPallatePresets() {

    $(".shls-pallate-presets-wrapper").siblings().not(".shls-pallate-functions-tabbar-wrapper").hide();
    $(".shls-pallate-presets-wrapper").show();

}

function renderColorWheel() {

    const rootEl = ".shls-pallate-iro-colorwheel";
    const shadowId = "shls-pallate-iro-colorwheel-shadow";

    // Initiate iro.js color wheel
    const wheel = new iro.ColorPicker(rootEl, {
        width: 250,
        layout: [ { component: iro.ui.Wheel } ],
        handleSvg: "#shls-pallate-iro-colorwheel-handle",
        borderColor: "#fff",
        borderWidth: 2
    });

    // Add shadow to wheel
    const defs = $(rootEl).find("defs");

    defs.html(
        `<filter id="${shadowId}">
             <feDropShadow dx="0" dy="5" stdDeviation="8"/>
         </filter>`
        + defs.html()
    );

    $(rootEl).find(".IroWheelBorder").attr("filter", `url(#${shadowId})`);

    // Add listener and change theme accordingly
    wheel.on('color:change', (color) => {

        const rgb = parseInt(color.hexString.substring(1), 16);   // convert rrggbb to decimal
        const r = (rgb >> 16) & 0xff;  // extract red
        const g = (rgb >>  8) & 0xff;  // extract green
        const b = (rgb >>  0) & 0xff;  // extract blue
        const luma = 0.2126 * r + 0.7152 * g + 0.0722 * b; // per ITU-R BT.709

        if (luma < 200) $("html").attr("style","--mdc-theme-secondary: " + color.hexString);
        else $("html").attr("style","--mdc-theme-secondary: var(--mdc-theme-primary)");

    });

}

function renderSwiper() {

    new Swiper('.swiper-container', {
        slidesPerView: 3,
        spaceBetween: 20,
        centeredSlides: true,
        slideToClickedSlide: true,
        pagination: {
            el: '.swiper-pagination',
            dynamicBullets: true,
            clickable: true
        },
    });

}