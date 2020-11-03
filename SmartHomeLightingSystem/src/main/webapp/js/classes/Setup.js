class Setup {

    p; q;
    r; g; b; a;
    anim;
    preset;
    doLDR;

    constructor(obj) {

        this.p = obj.p;
        this.q = obj.q;
        this.r = obj.r;
        this.g = obj.g;
        this.b = obj.b;
        this.a = obj.a;
        this.anim = obj.animId;
        this.preset = obj.presetId;
        this.doLDR = obj.doLDR;

    }

}