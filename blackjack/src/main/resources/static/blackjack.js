const apiBase = "http://localhost:8080/blackjack";

const startBtn = document.getElementById("startBtn");
const hitBtn = document.getElementById("hitBtn");
const standBtn = document.getElementById("standBtn");
const dealerHandDiv = document.getElementById("dealerHand");
const playerHandDiv = document.getElementById("playerHand");
const playerPointsP = document.getElementById("playerPoints");
const statusP = document.getElementById("status");

let dealerInterval;

startBtn.onclick = async () => {
    const res = await fetch(`${apiBase}/start`, { method: "POST" });
    const data = await res.json();
    updateUI(data);

    hitBtn.disabled = false;
    standBtn.disabled = false;
    startBtn.disabled = true;
};

hitBtn.onclick = async () => {
    const res = await fetch(`${apiBase}/player/hit`, { method: "POST" });
    const data = await res.json();
    updateUI(data);

    if (data.status !== "in-progress" && data.playerStands) {
        hitBtn.disabled = true;
        standBtn.disabled = true;
        playDealerStep();
    }
};

standBtn.onclick = async () => {
    await fetch(`${apiBase}/player/stand`, { method: "POST" });
    hitBtn.disabled = true;
    standBtn.disabled = true;
    playDealerStep();
};


// Animate dealer drawing one card at a time
async function playDealerStep() {
    dealerInterval = setInterval(async () => {
        const res = await fetch(`${apiBase}/dealer/next`, { method: "POST" });
        const data = await res.json();
        updateUI(data);

        if (data.dealerStands) {
            clearInterval(dealerInterval);
        }
    }, 1000); // 1 second between draws
}

function updateUI(data) {
    // Player
    playerHandDiv.innerHTML = "";
    data.playerHand.forEach(card => {
        const div = document.createElement("div");
        div.className = "card";
        div.textContent = card;
        playerHandDiv.appendChild(div);
    });
    playerPointsP.textContent = `Points: ${data.playerPoints}`;

    // Dealer
    dealerHandDiv.innerHTML = "";
    data.dealerHand.forEach(card => {
        const div = document.createElement("div");
        div.className = "card";
        div.textContent = card;
        dealerHandDiv.appendChild(div);
    });

    // Status
    statusP.textContent = data.status;

    if (data.status !== "in-progress") {
        hitBtn.disabled = true;
        standBtn.disabled = true;
        startBtn.disabled = false;
    }
}
