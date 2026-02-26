<template>
    <div class="product-form">
        <h2>新增商品</h2>
        <form @submit.prevent="submitProduct">
            <div>
                <label>商品編號</label>
                <input v-model="product.id" type="text" required />
            </div>
            <div>
                <label>商品名稱</label>
                <input v-model="product.name" type="text" required />
            </div>
            <div>
                <label>售價</label>
                <input v-model="product.price" type="number" required />
            </div>
            <div>
                <label>庫存</label>
                <input v-model="product.quantity" type="number" required />
            </div>
            <button type="submit">新增商品</button>
        </form>
        <p v-if="message">{{ message }}</p>
    </div>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';

const product = ref({
    id: '',
    name: '',
    price: 0,
    quantity: 0
});
const message = ref('');

const submitProduct = async () => {
    try {
        const response = await axios.post('http://localhost:8080/api/products/add', product.value);
        message.value = response.data;
        product.value = {
            productId: '',
            productName: '',
            price: 0,
            quantity: 0
        };
    } catch (error) {
        message.value = '新增失敗' + (error.response?.data || error.message);
    }
}
</script>

<style scoped>
.product-form {
    border: 1px solid #ccc;
    padding: 20px;
    margin-bottom: 20px;
}

div {
    margin-bottom: 10px;
}
</style>