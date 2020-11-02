class LDRGraph {

    scope = "def";
    mobile = true;

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
                        fontFamily: 'Roboto',
                        fontSize: 16,
                        padding: 20
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
        this.resize(this.mobile);

    }

    load(scope) {

        this.scope = scope;
        const raw = loadLDRData(this.scope);

        raw.labels[0] = "";
        raw.labels[raw.labels.length - 1] = "";
        this.obj.data.labels = raw.labels;
        this.obj.data.datasets[0].data = raw.data.map(v => 100 * v);

        this.obj.update();

    }

    resize(mobile) {

        this.mobile = mobile;

        let tickLim;
        if (this.mobile) tickLim = this.obj.data.datasets[0].data.length / 2;
        else tickLim = this.obj.data.datasets[0].data.length;
        this.obj.options.scales.xAxes[0].ticks.maxTicksLimit = tickLim;

        this.obj.update();

    }

}