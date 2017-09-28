import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes} from '@angular/router';


import { AppComponent } from './app.component';
import { SignupComponentComponent } from './signup-component/signup-component.component';
import { SingninComponent } from './singnin/singnin.component';


const appRoutes : Routes  = [
	{ path: 'signin' , component: SingninComponent},
	{ path: 'signup' , component: SignupComponentComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    SignupComponentComponent,
    SingninComponent
    
  ],
  imports: [
  	RouterModule.forRoot(
  		appRoutes,
  		{enableTracing: true}),
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
