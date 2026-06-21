async function reset_after_removing() {

    const table_body = document.getElementById("full-history-table-body");

    table_body.innerHTML = "";

    try {

        const response = await fetch('/translate/get/full-history');

        const data = await response.json();

        data.forEach(raw => {

            draw_full_table_body(raw);

        });


    } catch (e) {

        console.error(e);

    }

}

async function draw_full_table_body(raw) {

    const table_body = document.getElementById("full-history-table-body");

    const tr = document.createElement("tr");

    const td1 = document.createElement("td");
    const td2 = document.createElement("td");
    const td3 = document.createElement("td");
    const td4 = document.createElement("td");

    const button = document.createElement("button");

    td1.innerText = raw.time;
    td2.innerText = raw.wrongString;
    td3.innerText = raw.rightString;

    button.id = raw.id;
    button.onclick = delete_target_button;
    button.className = "btn btn-danger w-30";
    button.innerText = "Remove";

    td4.appendChild(button);

    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    tr.appendChild(td4);

    table_body.appendChild(tr);

}