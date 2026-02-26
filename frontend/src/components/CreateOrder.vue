<template>
    <div class="order-form">
        <h2>建立訂單</h2>
        <button @click="fetchProducts">重新整理商品清單</button>
        <div v-if="products.length === 0">暫無商品</div>
        <table v-else border="1" style="margin-top: 10px; width: 100%;">
            <thead>
                <tr>
                    <th>選購</th>
                    <th>商品名稱</th>
                    <th>單價</th>
                    <th>剩下商品</th>
                    <th>小計</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="item in products" :key="item.id">
                    <td><input type="checkbox" v-model="item.selected" /></td>
                    <td>{{ item.name }}</td>
                    <td>{{ item.price }}</td>
                    <td>{{ item.quantity }}</td>
                    <td>
                        <input type="number" v-model="item.buyQty" :disabled="!item.selected" min="1"
                            :max="item.quantity" @input="checkQuantity(item)" />
                    </td>
                    <td>{{ item.selected ? item.price * item.buyQty : 0 }}</td>
                </tr>
            </tbody>
        </table>
        <div style="margin-top: 20px;">
            <h3>訂單總金額{{ totalPrice }}元</h3>
            <button @click="submitOrder" :disabled="selectedItems.length === 0">建立訂單</button>
        </div>
        <p v-if="message">{{ message }}</p>
    </div>
</template>

<script setup>
import axios from 'axios';
import { computed, onMounted, ref } from 'vue';

const products = ref([]);
const message = ref('');

const props = defineProps({
  memberId: String
});

onMounted(() => {
    fetchProducts();
});

const fetchProducts = async () => {
    try {
        const response = await axios.get('http://localhost:8080/api/products/available');
        products.value = response.data.map(p => ({
            ...p,
            selected: false,
            buyQty: 1
        }));
    } catch (error) {
        console.error("無法讀取商品", error);
    }
};

const checkQuantity = (item) => {
    if (item.buyQty > item.quantity) {
        alert(`[${item.name}]購買數量不能大於庫存(${item.quantity})`);
        item.buyQty = item.quantity;
    }
    if (item.buyQty < 1) item.buyQty = 1;
};

const selectedItems = computed(() => {
    return products.value.filter(p => p.selected);
});

const totalPrice = computed(() => {
    return selectedItems.value.reduce((sum, item) => sum + (item.price * item.buyQty), 0);
});

const submitOrder = async () => {
    const orderItems = selectedItems.value.map(item => ({
        productId: item.id,
        quantity: item.buyQty,
        standPrice: item.price
    }));

    const requestBody = {
        memberId: props.memberId,
        items: orderItems
    };

    try {
        const response = await axios.post('http://localhost:8080/api/orders', requestBody);
        message.value = response.data;
        fetchProducts();
    } catch (error) {
        message.value = '訂單建立失敗：' + (error.response?.data || error.message);
    }
};
</script>

<style scoped>
.order-form {
    border: 1px solid #ccc;
    padding: 20px;
}

th,
td {
    padding: 8px;
    text-align: center;
}
</style>