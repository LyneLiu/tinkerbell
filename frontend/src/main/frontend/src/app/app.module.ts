import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

/*import本地组件*/
import { AppComponent } from './app.component';
import { OwnerComponent } from './components/owner.component'

@NgModule({
  declarations: [
    AppComponent, OwnerComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
