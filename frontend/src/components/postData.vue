<template>
    <div>
        <label for="myPostButton" class="postButtonLabel" @click="onClick">Сохранить</label>
        <button id="myPostButton" class="postButton"></button>
    </div>
</template>

<script>
    export default {
        name: "postData",
        props: ["excelData","imgFile"],
        methods: {
            onClick(){
                if(this.excelData!=null && this.excelData.length === 0){
                    alert("Фиаско");
                    return;
                }
                if(this.imgFile == null){
                    alert("Фиаско");
                    return;
                }
                const fd = new FormData();
                fd.append('excelData', JSON.stringify(this.excelData));//Велосипед
                fd.append('file', this.imgFile, this.imgFile.name);

                this.$http.post('http://localhost:8080/uploadFormData', fd).then(response =>{
                    if(response.data){
                        alert("Успех");
                    }else{
                        alert("Фиаско");
                    }});
            }
        }
    }
</script>

<style scoped>
    .postButtonLabel{
        padding: 5px 10px;
        border: 1px ridge black;
        background-color: white;
    }
    .postButton{
        opacity: 0;
        width: 0px;
    }


</style>