class ColorWheel {

    obj;
    size;
    targetQuery;
    $target;

    constructor(targetQuery, size) {

        this.targetQuery = targetQuery;
        this.size = size;

        this.$target = $(targetQuery);
        this.$target.empty().width(size + "px"); // Clear target from old wheel

        // Initialize
        const options = {
            width: this.size,
            color: COLOR,
            layout: [ { component: iro.ui.Wheel } ],
            handleSvg: "#" + this.targetQuery.substring(1) + "-handle",
            borderColor: "#fff",
            borderWidth: 2
        };

        this.obj = iro.ColorPicker(this.targetQuery, options);
        this.obj.on('color:change', (color) => handleColorChange(color.hexString));

        // Add shadow
        const $defs = this.$target.find("defs");
        const shadowId = this.targetQuery.substring(1) + "-shadow";

        $defs.html(
            `<filter id="${shadowId}">
                <feDropShadow dx="0" dy="5" stdDeviation="8"/>
            </filter>`
            + $defs.html()
        );

        this.$target.find(".IroWheelBorder").attr("filter", `url(#${shadowId})`);

    }

}