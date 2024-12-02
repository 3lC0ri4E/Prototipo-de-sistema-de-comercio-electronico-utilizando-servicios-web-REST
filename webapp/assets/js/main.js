// Función para registrar un artículo
function registrarArticulo() {
    const nombre = document.getElementById('nombre').value;
    const descripcion = document.getElementById('descripcion').value;
    const precio = document.getElementById('precio').value;
    const cantidad = document.getElementById('cantidad').value;
    const fotoBase64 = document.getElementById('foto').value; // Foto en formato base64

    fetch('/registrarArticulo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            nombre: nombre,
            descripcion: descripcion,
            precio: precio,
            cantidad: cantidad,
            fotoBase64: fotoBase64,
        }),
    })
    .then(response => response.json())
    .then(data => alert('Artículo registrado con éxito'))
    .catch(error => console.error('Error:', error));
}

// Función para buscar artículos
function buscarArticulos() {
    const keyword = document.getElementById('keyword').value;

    fetch(`/buscarArticulos?keyword=${keyword}`)
        .then(response => response.json())
        .then(data => {
            let results = document.getElementById('searchResults');
            results.innerHTML = ''; // Limpiar resultados previos

            data.forEach(articulo => {
                const div = document.createElement('div');
                div.innerHTML = `
                    <img src="data:image/jpeg;base64,${articulo.fotoBase64}" alt="${articulo.nombre}">
                    <p>${articulo.nombre}</p>
                    <p>${articulo.descripcion}</p>
                    <p>${articulo.precio}</p>
                    <button onclick="comprarArticulo(${articulo.idArticulo})">Comprar</button>
                `;
                results.appendChild(div);
            });
        })
        .catch(error => console.error('Error:', error));
}

// Función para realizar una compra
function comprarArticulo(idArticulo) {
    const cantidad = document.getElementById('cantidad').value;

    fetch('/comprarArticulo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            idArticulo: idArticulo,
            cantidad: cantidad,
        }),
    })
    .then(response => {
        if (response.status === 200) {
            alert('Compra realizada con éxito');
        } else {
            alert('Error en la compra');
        }
    })
    .catch(error => console.error('Error:', error));
}
