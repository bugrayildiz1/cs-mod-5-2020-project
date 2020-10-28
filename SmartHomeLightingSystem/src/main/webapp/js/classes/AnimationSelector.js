class AnimationSelector {

    constructor(targetQuery) {

        const options = {
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
        };

        return new Swiper(targetQuery, options);

    }

}