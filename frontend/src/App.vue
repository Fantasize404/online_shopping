<template>
  <div class="container">
    <h1>電商購物中心</h1>
    <div v-if="!currentUser">
      <Member @login-success="handleLogin"></Member>
    </div>
    <div v-else>
      <div class="user-info">
        <h3>Welcome {{ currentUser.memberName }}</h3>
        <button @click="handleLogout">登出</button>
      </div>
      <hr />
      <AddProduct v-if="currentUser.role === 'ADMIN'"></AddProduct>
      <CreateOrder :memberId="currentUser.memberId"></CreateOrder>
      <OrderDetail></OrderDetail>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import AddProduct from './components/AddProduct.vue'
import CreateOrder from './components/CreateOrder.vue';
import Member from './components/Member.vue';
import OrderDetail from './components/OrderDetail.vue';

const currentUser = ref(null);

onMounted(() => {
  const savedUser = localStorage.getItem('ecommerce_user')
  if (savedUser) {
    currentUser.value = JSON.parse(savedUser);
  }
});

const handleLogin = (userData) => {
  currentUser.value = userData;
  localStorage.setItem('ecommerce_user', JSON.stringify(userData));
  localStorage.setItem('memberId', userData.memberId);
};

const handleLogout = () => {
  currentUser.value = null;
  localStorage.removeItem('ecommerce_user');
  localStorage.removeItem('memberId');
};
</script>

<style scoped>
.container {
  max-width: 800px;
  margin: 0 auto;
  font-family: Arial, Helvetica, sans-serif;
}

.user-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}
</style>