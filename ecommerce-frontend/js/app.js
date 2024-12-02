// app.js

const apiUrl = 'http://localhost:8080/api';  // URL del backend (ajustar según sea necesario)

// Función para obtener todos los artículos
async function fetchArticles(keyword = '') {
    const response = await fetch(`${apiUrl}/articles?search=${keyword}`);
    const articles = await response.json();
    return articles;
}
