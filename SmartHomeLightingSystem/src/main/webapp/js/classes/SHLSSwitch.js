class SHLSSwitch {

    id;
    obj;
    target;
    action;
    state;
    default;

    constructor(options) {

        this.id = options.id;
        this.action = options.action;
        this.default = options.default;
        this.target = document.querySelector(options.target);

        this.obj = new mdc.switchControl.MDCSwitch(this.target);
        this.obj.root.addEventListener("change", () => this.toggle(this));

        const init = JSON.parse(localStorage.getItem(this.id));

        if (init === null) {

            this.update(this, this.default);
            localStorage.setItem(this.id, this.default);

        } else if (init) this.update(this, true);
        else this.update(this, false);

    }

    toggle(o) {

        o.state = !o.state;
        localStorage.setItem(o.id, o.state);
        o.action();

    }

    update(o, b) {

        o.obj.checked = b;
        o.state = b;

    }

}