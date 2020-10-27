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

        this.update();

    }

    update() {

        const raw = loadLDRData(this.mobile, this.scope);
        this.obj.data.labels = raw.labels;
        this.obj.data.datasets[0].data = raw.data;

    }

}