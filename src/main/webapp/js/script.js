Vue.prototype.$bus = new Vue({});

Vue.component("top_bar", {
    template: '#menu',
    data: function() {
        return {
            selected_item: ''
        }
    },
    methods: {
        swap: function() {
            this.selected_item = "registar";
            this.$bus.$emit('change_register', this.selected_item);
        },
        swap1: function() {
            this.selected_item = "listar";
            this.$bus.$emit('change_list', this.selected_item);
        }
    }

});
Vue.component("register", {
    template: '#registo',
    data: function() {
        return {
            nome: '',
            apelido: '',
            curso: '',
            faculdade: '',
            validation_status: '',
            response_register: '',
            name_validation: false,
            surname_validation: false,
            course_validation: false,
            faculdade_validation: false,
            page:''
        }
    },

    created() {
        this.$bus.$on('change_register', val => this.page = val);
        this.$bus.$on('change_list', val1 => this.page = val1);
    },

    methods: {
        register: function() {
            var timeout = 3000;
            if (this.nome.length < 3) {
                this.validation_status = "Por favor digite o seu nome!";
                this.name_validation = true;
                setTimeout(() => {
                    this.validation_status = "";
                    this.name_validation = false;
                }, timeout);
                return false;
            } else if (this.apelido.length < 3) {
                this.validation_status = "Por favor digite o seu apelido!";
                this.surname_validation = true;
                setTimeout(() => {
                    this.validation_status = "";
                    this.surname_validation = false;
                }, timeout);
                return false;

            } else if (this.curso.length < 3) {
                this.validation_status = "Por favor digite o seu curso!";
                this.course_validation = true;
                setTimeout(() => {
                    this.validation_status = "";
                    this.course_validation = false;
                }, timeout);
                return false;

            } else if (this.faculdade.length < 3) {
                this.validation_status = "Por favor digite o nome da sua faculdade!";
                this.faculdade_validation = true;
                setTimeout(() => {
                    this.validation_status = "";
                    this.faculdade_validation = false;
                }, timeout);
                return false;

            } else { 
                    
                  axios.post("http://desktop-lgoe29v:8080/donzane/runService/insert",{
                            "name":this.nome,
                            "surname":this.apelido,
                            "course":this.curso,
                            "college":this.faculdade
                  })
                     
                        .then(response=>{
                                this.response_register = response.data;
                    
                               if(this.response_register.insert_status == 1){
                                 $("#success").modal("show");
                                 setTimeout(() => {
                                                this.nome ="";
                                                this.apelido ="";
                                                this.curso ="";
                                                this.faculdade = "";
                                                this.response_register ="";
                                                $("#success").modal("hide");
                                 }, 3500);
                                    
                                     
                                 }else{
                                    $("#failed").modal("show");
                                     setTimeout(() => {
                                           $("#success").modal("hide");
                                 }, 3500);
                                 }
                        },(error)=>{
                                console.log(error);
                    })
            }
        }

    }
});
      
   Vue.component("list_student",{
            template:'#listar',
            data:function() {
                return{
                    page:'',
                    response:'',
                    response_all:'',
                    pagination:false,
                    pageNum:0,
                    response_delete:null,
                    confirmar:0,
                    total_page:null,
                    new_value_delete:'',
                    oldPage:'',
                    id_to_be_delete:0
                }
            },
        created(){
            this.$bus.$on('change_register', val => this.page = val);
            this.$bus.$on('change_list', val1 => this.page = val1);
            history.pushState("Lista de estudante","?pagina=1");
            axios.post("http://desktop-lgoe29v:8080/donzane/runService/list")
              .then(response=>{
                        this.response_all = JSON.parse(JSON.stringify(response.data));
                        this.total_page = this.response_all.total;
                         
                        if (this.total_page >1){
                            this.pagination = true;
                        }else{
                            this.pagination =true;

                        }
              }),(error)=>{

              }
        },
          watch:{
             id_to_be_delete(newValue){
                  this.new_value_delete = newValue;
                this.prepare_delete(this.newPage,this.oldPage);
             },
            pageNum(newValue1){
                this.pageNum = newValue1;
            }
             
          },

        methods:{
               prepare_delete(id){
                  $('#delete').modal("show");
                   this.new_value_delete = id;
               },
              
            paginar:function(param){
                 this.pageNum = param;
                 history.pushState({pagina:this.pageNum},"Lista de estudante","?pagina="+this.pageNum);
                axios.post("http://desktop-lgoe29v:8080/donzane/runService/list",{
                    "pagina":this.pageNum
                })
                .then(response=>{
                    this.response_all = JSON.parse(JSON.stringify(response.data));
                    this.total_page = this.response_all.total;
                }),(error)=>{
                        
                }
                                   
            },
            delete_student:function (id) {
                $('#delete').modal("hide");
               axios.post("http://desktop-lgoe29v:8080/donzane/runService/delete",{
                     "id_delete":id
                })
                .then(response=>{
                       this.response_delete = response.data;
                        if(this.response_delete.delete_status==1){
                           $("#sucessDelete").modal("show");
                           setTimeout(() => {
                            $("#sucessDelete").modal("hide");

                            if(this.pageNum ==0){
                                this.paginar(1);
                           }else{
                               this.paginar(this.pageNum);
                           }

                           }, 3000);
                       }
                })
                },

            cancelarOperacao(){
                $('#delete').modal("hide");
            },
            
        }

   });

var app = new Vue({
    el: '#app',
    data: {}
});