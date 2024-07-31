window.addEventListener("load", function(){

    var the_file_element = document.getElementById("the_file1");
    var the_file_element_1 = document.getElementById("the_file2");
    var the_file_element_2 = document.getElementById("the_file3");
    
    the_file_element.addEventListener("change", function(e){          
      
      // 寫在這
      var picture_list = document.getElementsByClassName("picture_list1")[0];
      picture_list.innerHTML = ""; // 清空
    
      // 跑每個使用者選的檔案，留意 i 的部份
      for (let i = 0; i < this.files.length; i++) {
    
        let reader = new FileReader(); // 用來讀取檔案
    
        reader.readAsDataURL(this.files[i]); // 讀取檔案
    
        reader.addEventListener("load", function () {
          console.log(reader.result);
    
    
          let li_html = `
            <li><img src="${reader.result}" class="preview"></li>
          `;
          picture_list.insertAdjacentHTML("beforeend", li_html); // 加進節點
    
        });
    
    }
    });
    the_file_element_1.addEventListener("change", function(e){          
      
      // 寫在這
      var picture_list_1 = document.getElementsByClassName("picture_list2")[0];
      picture_list_1.innerHTML = ""; // 清空
    
      // 跑每個使用者選的檔案，留意 i 的部份
      for (let i = 0; i < this.files.length; i++) {
    
        let reader = new FileReader(); // 用來讀取檔案
    
        reader.readAsDataURL(this.files[i]); // 讀取檔案
    
        reader.addEventListener("load", function () {
          console.log(reader.result);
    
    
          let li_html_m1 = `
            <li><img src="${reader.result}" class="preview"></li>
          `;
          picture_list_1.insertAdjacentHTML("beforeend", li_html_m1); // 加進節點
    
        });
        
    
      }
      
      
    });
    
    the_file_element_2.addEventListener("change", function(e){          
      
      // 寫在這
      var picture_list_2 = document.getElementsByClassName("picture_list3")[0];
      picture_list_2.innerHTML = ""; // 清空
    
      // 跑每個使用者選的檔案，留意 i 的部份
      for (let i = 0; i < this.files.length; i++) {
    
        let reader = new FileReader(); // 用來讀取檔案
    
        reader.readAsDataURL(this.files[i]); // 讀取檔案
    
        reader.addEventListener("load", function () {
          console.log(reader.result);
    
    
          let li_html_m2 = `
            <li><img src="${reader.result}" class="preview"></li>
          `;
          picture_list_2.insertAdjacentHTML("beforeend", li_html_m2); // 加進節點
    
        });
        
    
      }
      
      
    });
});