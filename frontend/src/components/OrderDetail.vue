<template>
    <div class="order-detail-form">
        <h2 class="page-title">訂單明細</h2>
        <div v-if="loading">資料載入中</div>
        <div v-else-if="groupedOrders.length === 0">目前沒有訂單紀錄</div>
        <div v-else>
            <div v-for="order in groupedOrders" :key="order.orderId">
                <div>
                    <div>
                        <span>訂單編號{{ order.orderId }}</span>
                    </div>
                    <div>
                        <span>總金額</span>
                        <span>NT$ {{ formatCurrency(order.totalPrice) }}</span>
                    </div>
                </div>
                <div>
                    <table border="1" style="margin-top: 10px; width: 100%;">
                        <thead>
                            <tr>
                                <th>商品名稱</th>
                                <th>單價</th>
                                <th>數量</th>
                                <th>小計</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(item, index) in order.items" :key="index">
                                <td>{{ item.productName }}</td>
                                <td>${{ formatCurrency(item.standPrice) }}</td>
                                <td>{{ item.quantity }}</td>
                                <td>${{ formatCurrency(item.itemPrice) }}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const loading = ref(false);
const groupedOrders = ref([]);

const currentMemberId = ref(localStorage.getItem('memberId') || 'M001');

const formatCurrency = (value) => {
    if (!value) return '0';
    return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
};

const fetchOrderHistory = async () => {
    loading.value = true;
    try {
        const response = await axios.post('http://localhost:8080/api/orderdetails', {
            memberId: currentMemberId.value
        });
        const rawData = response.data;
        const orderMap = {};

        rawData.forEach(row => {
            if (!orderMap[row.orderId]) {
                orderMap[row.orderId] = {
                    orderId: row.orderId,
                    totalPrice: 0,
                    items: []
                };
            }

            orderMap[row.orderId].totalPrice += row.itemPrice;

            orderMap[row.orderId].items.push({
                productId: row.productId,
                productName: row.productName,
                quantity: row.quantity,
                standPrice: row.standPrice,
                itemPrice: row.itemPrice
            });
        });

        groupedOrders.value = Object.values(orderMap).reverse();

    } catch (error) {
        console.error("取得訂單失敗", error);
        alert("取得訂單失敗，請確認後端伺服器是否正常啟動！");
    } finally {
        loading.value = false;
    }
};

onMounted(() => {
    fetchOrderHistory();
});
</script>

<style scoped>
.order-detail-form {
    border: 1px solid #ccc;
    padding: 20px;
}

th,
td {
    padding: 8px;
    text-align: center;
}
</style>