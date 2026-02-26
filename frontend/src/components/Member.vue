<template>
    <div class="member-form">
        <h2 v-if="isLoginMode">登入</h2>
        <h2 v-else>註冊</h2>
        <form @submit.prevent="handleSubmit">
            <div>
                <label>帳號</label>
                <input v-model="form.account" type="text" required="" />
            </div>
            <div>
                <label>密碼</label>
                <input v-model="form.password" type="text" required="" />
            </div>
            <div v-if="!isLoginMode">
                <label>姓名</label>
                <input v-model="form.name" type="text" required="" />
            </div>
            <button type="submit">{{ isLoginMode ? '登入' : '註冊' }}</button>
        </form>
        <p v-if="message" class="error-msg">{{ message }}</p>
        <button @click="toggleMode" class="toggle-btn">{{ isLoginMode ? '點擊註冊' : '點擊登入' }}</button>
    </div>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';

const emit = defineEmits(['login-success']);
const isLoginMode = ref(true);
const message = ref('');

const form = ref({
    account: '',
    password: '',
    name: ''
});

const toggleMode = () => {
    isLoginMode.value = !isLoginMode.value;
    message.value = '';
    form.value = {
        account: '',
        password: '',
        name: ''
    }
};

const handleSubmit = async () => {
    message.value = '';
    try {
        if (isLoginMode.value) {
            const response = await axios.post('http://localhost:8080/api/members/login', {
                account: form.value.account,
                password: form.value.password
            });
            emit('login-success', response.data)
        } else {
            await axios.post('http://localhost:8080/api/members/register', form.value);
            message.value = '註冊成功，請重新登入';
            isLoginMode.value = true;
        }

    } catch (error) {
        message.value = (isLoginMode.value ? '登入失敗' : '註冊失敗') + (error.response.data || error.message);
    }
};
</script>

<style scoped>
.member-form {
    border: 1px solid #ccc;
    padding: 20px;
    max-width: 400px;
    margin: 0 auto;
}

div {
    margin-bottom: 10px;
}

.error-msg {
    color: red;
}

.toggle-btn {
    margin-top: 15px;
    background: none;
    border: blue;
    cursor: pointer;
    text-decoration: underline;
}
</style>