// shoppingCart.js

let cart = JSON.parse(localStorage.getItem('cart')) || [];

function renderCart() {
    const cartContainer = document.getElementById('cart-items');
    const totalContainer = document.getElementById('total-price');
    cartContainer.innerHTML = '';
    let total = 0;

    cart.forEach(item => {
        const itemElement = document.createElement('div');
        itemElement.classList.add('cart-item');
        itemElement.innerHTML = `
            <span>${item.name}</span>
            <span>${item.quantity} x $${item.price}</span>
            <button onclick="removeFromCart(${item.id})">Eliminar</button>
        `;
        cartContainer.appendChild(itemElement);
        total += item.quantity * item.price;
    });

    totalContainer.innerHTML = `Total: $${total}`;
}

function addToCart(id) {
    const article = cart.find(item => item.id === id);

    if (article) {
        article.quantity += 1;
    } else {
        cart.push({ id, quantity: 1, ...article });
    }

    localStorage.setItem('cart', JSON.stringify(cart));
    renderCart();
}

function removeFromCart(id) {
    cart = cart.filter(item => item.id !== id);
    localStorage.setItem('cart', JSON.stringify(cart));
    renderCart();
}

document.getElementById('clear-cart').addEventListener('click', function() {
    localStorage.removeItem('cart');
    cart = [];
    renderCart();
});

renderCart();
