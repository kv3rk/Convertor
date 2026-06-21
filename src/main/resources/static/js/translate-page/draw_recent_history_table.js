async function draw_recent_history_table() {

    try {

        const result = await fetch('/translate/get/recent-history');

        const data = await result.json();

        const recent_history_table = document.getElementById("recent-history-table");

        recent_history_table.innerHTML = "";

        data.forEach(raw => {

            draw_recent_history_raw(raw);

        })

    } catch (e) {
        console.error(e);
    }

}

async function draw_recent_history_raw(raw) {

    const table = document.getElementById("recent-history-table");

    const tr = document.createElement("tr");

    const td1 = document.createElement("td");
    td1.innerText = raw.wrongString;

    const td2 = document.createElement("td");
    td2.innerText = raw.rightString;

    tr.appendChild(td1);
    tr.appendChild(td2)

    table.appendChild(tr);

}