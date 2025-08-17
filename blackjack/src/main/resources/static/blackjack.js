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
        const img = document.createElement("img");
        img.src = cardToImageUrl(card);
        img.className = "card-img";
        playerHandDiv.appendChild(img);
    });

    // Dealer
    dealerHandDiv.innerHTML = "";
    data.dealerHand.forEach(card => {
        const img = document.createElement("img");
        img.src = cardToImageUrl(card);
        img.className = "card-img";
        dealerHandDiv.appendChild(img);
    });


    // Status
    statusP.textContent = data.status;

    if (data.status !== "in-progress") {
        hitBtn.disabled = true;
        standBtn.disabled = true;
        startBtn.disabled = false;
    }
}

// Utility function to map card string to image filename
function cardToImageUrl(cardString) {
    if (cardString === "?") {
        return "assets/back.png"; // hidden card
    }

    // Split rank and suit
    const rankChar = cardString.slice(0, -1);  // e.g. "10", "A", "K"
    const suitChar = cardString.slice(-1); // e.g. "♠", "♥", "♦", "♣"

    // Map suit symbols to words
    const suitMap = {
        "s": "spades",
        "h": "hearts",
        "d": "diamonds",
        "c": "clubs"
    };

    // Map rank to strings
    const rankMap = {
        "A": "ace",
        "K": "king",
        "Q": "queen",
        "J": "jack",
        "10": "10",
        "9": "9",
        "8": "8",
        "7": "7",
        "6": "6",
        "5": "5",
        "4": "4",
        "3": "3",
        "2": "2",
    };

    const suit = suitMap[suitChar];
    const rank = rankMap[rankChar];
    const fileName = `${rank}_of_${suit}.png`;

    return `assets/${fileName}`;
}
