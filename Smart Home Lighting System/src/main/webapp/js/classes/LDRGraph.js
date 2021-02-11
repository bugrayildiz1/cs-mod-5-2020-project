class LDRGraph {

    scope = "def";
    mobile = true;
    sizes = {
        mobile: {
            def: () => { return Math.round(this.obj.data.datasets[0].data.length / 2); },
            day: () => { return 7; },
            week: () => { return this.obj.data.datasets[0].data.length; },
            month: () => { return Math.round(this.obj.data.datasets[0].data.length / 5); }
        },
        desktop: {
            def: () => { return this.obj.data.datasets[0].data.length; },
            day: () => { return 14; },
            week: () => { return this.obj.data.datasets[0].data.length; },
            month: () => { return Math.round(this.obj.data.datasets[0].data.length / 2); }
        }
    };

    constructor(targetQuery) {

        const data = {
            datasets: [{
                borderColor: $(":root").css("--mdc-theme-primary"),
                backgroundColor: $(":root").css("--mdc-theme-primary") + "33", // "33" is 20% opacity in hex
                borderWidth: 2,
                pointRadius: 0,
                pointHitRadius: 5
            }]
        };
        const options = {
            maintainAspectRatio: false,
            legend: { display: false },
            scales: {
                xAxes: [{
                    gridLines: {
                        tickMarkLength: 40,
                        zeroLineWidth: 0
                    },
                    ticks: {
                        padding: 20,
                        maxRotation: 0,
                        fontFamily: 'Roboto',
                        fontSize: 16
                    }
                }],
                yAxes: [{
                    display: false,
                    ticks: {
                        suggestedMin: 0,
                        suggestedMax: 120 // simulate top padding
                    }
                }]
            }
        };

        this.obj = new Chart($(targetQuery), {
            type: 'line',
            data,
            options
        });

        this.load(this.scope);

    }

    load(scope) {

        this.scope = scope;
        const raw = loadLDRData(this.scope);

        raw.labels[0] = "";
        raw.labels[raw.labels.length - 1] = "";
        this.obj.data.labels = raw.labels;
        this.obj.data.datasets[0].data = raw.data.map(v => 100 * v);

        this.resize(this.mobile);

    }

    resize(mobile) {

        this.mobile = mobile;
        let device = "";
        this.mobile ? device = "mobile" : device = "desktop";

        this.obj.options.scales.xAxes[0].ticks.maxTicksLimit = this.sizes[device][this.scope]();
        this.obj.update();

    }

}