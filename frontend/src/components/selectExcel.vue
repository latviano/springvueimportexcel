<template>
    <div>
        <label for="myExcFile" class="postExcelLabel">Выбрать excel файл</label>
        <input type="file" id="myExcFile" @change="onFileChange" accept=".xlsx,.xls" class="postExcel"/>
    </div>
</template>

<script>
    export default {
        name: "selectExcel",
        data() {
            return {
            }
        },
        props: ["excelData"],
        methods: {
            onFileChange(event) {
                if (event.target.files.length > 0) {
                    let file = event.target.files[0];
                    const fd = new FormData();
                    fd.append('file', file, file.name);
                    this.$http.post('http://localhost:8080/uploadExcelFile', fd).then(response =>
                        response.json().then(
                            data => this.$emit('processResult',data)));
                } else {
                    this.excelData = "";
                }
            },
        },
    }
</script>

<style>
    .postExcel{
        opacity: 0;
        width: 0px;
    }
    .postExcelLabel{
        padding: 5px 10px;
        border: 1px ridge black;
    }
</style>