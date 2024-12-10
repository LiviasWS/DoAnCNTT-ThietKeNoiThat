document.addEventListener("DOMContentLoaded", () => {
    const decreaseQuantity = () => {
        const quantity = document.getElementById("quantity");
        console.log("Decrease button clicked");
        let quantityValue = parseInt(quantity.value);
        if (quantityValue > 1) {
            quantity.value = quantityValue - 1;
        }
    };

    const increaseQuantity = () => {
        const quantity = document.getElementById("quantity");
        console.log("Increase button clicked");
        let quantityValue = parseInt(quantity.value);
        quantity.value = quantityValue + 1;
    };

    window.decreaseQuantity = decreaseQuantity;
    window.increaseQuantity = increaseQuantity;
});