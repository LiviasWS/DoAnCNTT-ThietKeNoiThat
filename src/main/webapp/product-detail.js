function decreaseQuantity()
{
    const quantity = document.getElementById("quantity");
    let quantityValue = parseInt(quantity.value); 
    if(quantityValue > 1)
    {
        quantity.value = quantityValue - 1;
    }
}
function increaseQuantity()
{
    const quantity = document.getElementById("quantity");
    let quantityValue = parseInt(quantity.value);
    quantity.value = quantityValue + 1;
}