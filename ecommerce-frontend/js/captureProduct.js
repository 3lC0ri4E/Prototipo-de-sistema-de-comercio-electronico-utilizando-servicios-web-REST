// captureProduct.js

document.getElementById('capture-product-form').addEventListener('submit', async function(event) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const productData = {
        name: formData.get('name'),
        description: formData.get('description'),
        price: parseFloat(formData.get('price')),
        quantity: parseInt(formData.get('quantity')),
        image: formData.get('image') // Se debe pasar la imagen como Base64
    };

    try {
        const response = await fetch(`${apiUrl}/articles`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(productData)
        });

        if (response.ok) {
            alert('Producto capturado exitosamente');
        } else {
            alert('Hubo un error al capturar el producto');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error al capturar el producto');
    }
});
