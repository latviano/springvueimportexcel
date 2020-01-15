<template>
    <div id="app" class="container">

        <div class="contBlock">
            <selectExcel @processResult="processResult" :excelData="excelData"/>
            <excelDataTable :excelData="excelData"/>
        </div>
        <div class="contBlock">
            <selectImage @changeImageUrl="changeImageUrl" :imgUrl="imgUrl"/>
        </div>
        <div class="contBlock">
            <postData :excelData="tempData" :imgFile="imgFile"/>
        </div>
    </div>
</template>

<script>
    import selectExcel from "./components/selectExcel";
    import excelDataTable from "./components/excelDataTable";
    import selectImage from "./components/selectImage";
    import postData from "./components/postData";

    export default {
        name: 'app',
        components: {
            selectExcel,
            excelDataTable,
            selectImage,
            postData,
        },
        data() {
            return {
                excelData: [],
                imgUrl: null,
                imgFile: null,
                tempData: null,
            }
        },
        methods: {
            processResult(data) {
                this.tempData = data;
                this.excelData = [];
                data.list.forEach(this.addToTable);
            },
            changeImageUrl(file) {
                this.imgFile = file;
                const url = URL.createObjectURL(file);
                this.imgUrl = url;
            },
            addToTable(newRow) {
                this.excelData = [...this.excelData, newRow];
            },
        },
    }
</script>

<style>
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }

    .container {
        display: grid;
        grid-template-rows: minmax(100px, auto);
        grid-template-columns: 1fr 1fr 1fr;
    }

    .contBlock {
        display: block;
    }

    label {
      cursor: pointer;
    }
</style>
