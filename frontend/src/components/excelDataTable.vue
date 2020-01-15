<template>
    <table class="excelData">
        <tr :key="item.id" v-for="item in excelData">
            <td>{{item.article}}</td>
            <td>
                <input class="in" v-model="item.imgPlace">
            </td>
        </tr>
    </table>
</template>

<script>
    export default {
        name: "excelDataTable",
        props: ["excelData"],
    }
</script>

<style scoped>
    .excelData {
        margin: auto;
        padding-top: 10px;
    }
    .in {
        margin-left: 50px;
        text-align: right;
    }
</style>