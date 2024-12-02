// searchProduct.js

document.getElementById('search-form').addEventListener('submit', async function(event) {
    event.preventDefault();

    const keyword = document.getElementById('search-input').value;
    const articles = await fetchArticles(keyword);
    const productContainer = document.getElementById('product-list');
    
    productContainer.innerHTML = '';

    articles.forEach(article => {
        const articleCard = document.createElement('div');
        articleCard.classList.add('product-card');
        articleCard.innerHTML = `
            <img src="data:image/jpeg;base64,${article.image}" alt="${article.name}">
            <h4>${article.name}</h4>
            <p>${article.description}</p>
            <p>$${article.price}</p>
            <button onclick="addToCart(${article.id})">Añadir al carrito</button>
        `;
        productContainer.appendChild(articleCard);
    });
});
