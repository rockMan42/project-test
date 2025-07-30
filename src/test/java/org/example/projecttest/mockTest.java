// package org.example.projecttest;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNull;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.Answers.RETURNS_SMART_NULLS;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyInt;
// import static org.mockito.ArgumentMatchers.anyString;
// import static org.mockito.ArgumentMatchers.argThat;
// import static org.mockito.ArgumentMatchers.eq;
// import static org.mockito.ArgumentMatchers.isA;
// import static org.mockito.ArgumentMatchers.isNull;
// import static org.mockito.Mockito.atLeast;
// import static org.mockito.Mockito.atMost;
// import static org.mockito.Mockito.doAnswer;
// import static org.mockito.Mockito.doCallRealMethod;
// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.doReturn;
// import static org.mockito.Mockito.doThrow;
// import static org.mockito.Mockito.inOrder;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.mockStatic;
// import static org.mockito.Mockito.never;
// import static org.mockito.Mockito.spy;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.verifyNoInteractions;
// import static org.mockito.Mockito.verifyNoMoreInteractions;
// import static org.mockito.Mockito.when;
// import static org.mockito.Mockito.withSettings;

// import java.util.ArrayList;
// import java.util.LinkedList;
// import java.util.List;
// import java.util.Random;

// import javax.management.RuntimeErrorException;

// import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.ArgumentCaptor;
// import org.mockito.Captor;
// import org.mockito.InOrder;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockSettings;
// import org.mockito.MockedStatic;
// import org.mockito.Mockito;
// import org.mockito.MockitoAnnotations;
// import org.mockito.Spy;
// import org.mockito.exceptions.verification.NoInteractionsWanted;
// import org.mockito.exceptions.verification.TooFewActualInvocations;
// import org.mockito.invocation.InvocationOnMock;
// import org.mockito.junit.MockitoJUnitRunner;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.mockito.stubbing.Answer;

// @ExtendWith(MockitoExtension.class) 
// public class mockTest {

//     List<String> mockList = Mockito.mock(List.class);

//     LinkedList mockLinkList = Mockito.mock(LinkedList.class);

//     @Mock   
//     private List<String> mockAnnotationList;

//     @Test
//     public void Test1(){
//         mockList.add("a");
//         mockList.clear();

//         verify(mockList).add("a"); //验证
//         verify(mockList,times(1)).clear();//验证以及字数
//     }

    
//     @Test
//     public void Test2(){
//         //打桩(stubbing)即指定mock对象的方法被调用时的行为（如返回值、抛出异常等）
//         when(mockLinkList.get(0)).thenReturn(100);
//         when(mockLinkList.get(1)).thenThrow(new RuntimeException());
        
//         //断言->判断是否是期待的值或者异常
//         assertEquals(100, mockLinkList.get(0));//断言值
//         assertThrows(RuntimeException.class, ()->mockLinkList.get(1));//断言异常
//         assertNull(mockLinkList.get(1111));

//         //参数匹配器
//         when(mockLinkList.get(anyInt())).thenReturn("element");
//         assertEquals("element", mockLinkList.get(999));
//         verify(mockLinkList).get(anyInt());

//         //验证是否调用add方法以及传入的参数是否是一个不为空且长度大于5的字符串
//         mockLinkList.add("mockLinkList");
//         verify(mockLinkList).add(argThat((String s)-> s!=null && s.length()>5));
        
//         //如果你使用参数匹配器，所有参数都必须由匹配器提供
//         //直接使用 eq("固定值") 只能验证某个具体的值，而 argThat 可以自定义任意复杂的条件。
//         verify(mockLinkList).add(anyInt(),anyString(),eq("合法参数"));//验证自定义的方法
//         // 上述代码是正确的,因为eq()也是一个参数匹配器
        
//         verify(mockLinkList).add(anyInt(),anyString(),"合法参数");
//         // 上述代码是错误的,因为所有参数必须由匹配器提供，而参数"third argument"并非由参数匹配器提供，因此的缘故会抛出异常

//     }

//     @Test
//     public void Test3(){
//       mockLinkList.add("once");
      
//       mockLinkList.add("twice");
//       mockLinkList.add("twice");

//       mockLinkList.add("three");
//       mockLinkList.add("three");
//       mockLinkList.add("three");
        
//       // 下面的两个验证函数效果一样,因为verify默认验证的就是times(1)
//       verify(mockLinkList).add("once");
//       verify(mockLinkList,times(1)).add("once");

//       //验证具体执行的次数
//       verify(mockLinkList,times(2)).add("twice");
//       verify(mockLinkList,times(3)).add("three");

//     }

//     //通过stub发送异常返回无效的函数
//     @Test
//     public void Test4(){
//         doThrow(RuntimeException.class).when(mockLinkList).clear();
//         // 调用这句代码会抛出异常
//         mockLinkList.clear();
//     }

//     //验证执行顺序
//     @Test
//     public void Test5(){
//         //验证一个mock对象的执行顺序
//         List singleList = mock(List.class);

//         singleList.add("was added first");
//         singleList.add("was added second");

//         //为模拟的对象创建一个inorder对象(验证顺序)
//          InOrder inOrder = inOrder(singleList);

//         // 确保add函数首先执行的是add("was added first"),然后才是add("was added second")
//          inOrder.verify(singleList).add("was added first");
//          inOrder.verify(singleList).add("was added second");

//          //验证多个mock对象的执行顺序
//          List firstMock = mock(List.class);
//          List secondMock = mock(List.class);

//          firstMock.add("was added first");
//          secondMock.add("was added second");

//          //为每个模拟的对象创建inorder对象
//          InOrder inOrder2 = inOrder(firstMock,secondMock);

//          inOrder2.verify(firstMock).add("was added first");
//          inOrder2.verify(secondMock).add("was added second");

//     }

//     //验证多余的调用
//     @Test
//     public void Test6(){
//         mockLinkList.add("one");
//         mockLinkList.add("two");

//         verify(mockLinkList).add("one");

//         //由于mockedList.add("two")被调用过，但没有验证，因此最后的测试将会失败
//         verifyNoMoreInteractions(mockLinkList);
//     }

//     //@Mock注解(可以使用@Mock注解将字段标记为mock对象，从而减少创建mock的重复代码，使测试类更易读)
//     @Test
//     public void mockAnnotation(){
//         mockAnnotationList.add("one");
//         verify(mockAnnotationList).add("one");
//     }


//     //确保交互(interaction)操作不会在mock对象上执行
//     @Test
//     public void Test7(){
//         mockAnnotationList.add("one");
//         //普通验证
//         verify(mockAnnotationList).add("one");

//         // 验证某个交互是否从未被执行
//         verify(mockAnnotationList,never()).add("tw");

//         // 验证某个交互是否从未被执行
//         List mockTwo = mock(List.class);
//         List mockThree = mock(List.class);
//         verifyNoInteractions(mockTwo,mockThree);
//     }

//     //为连续的调用做测试桩（存根）
//     @Test
//     public void Test8(){
//         //第一次调用抛出异常，第二次调用返回值，后面的所有的调用跟第二次一样
//         when(mockLinkList.add("fuck")).thenThrow(new RuntimeException()).thenReturn(false);
//         mockLinkList.add("fuck");
//         System.out.println(mockAnnotationList.add("fuck"));
//     }

//     //为回调做测试桩
//     @Test
//     public void Test9(){
//             when(mockLinkList.add(anyString())).thenAnswer(new Answer() {
//         Object answer(InvocationOnMock invocation) {
//             Object[] args = invocation.getArguments();
//             Object mock = invocation.getMock();
//             return "called with arguments: " + args;
//         }
//     });

    
//     System.out.println(mockLinkList.add("foo"));
//     }
    
//     //doReturn()、doThrow()、doAnswer()、doNothing()、doCallRealMethod()系列方法的运用
//     @Test
//     public void Test10(){
//         // doThrow(new RuntimeException()).when(mockLinkList).clear();

//         // //following throws RuntimeException:
//         // // 下面的代码会抛出异常
//         // mockLinkList.clear();
//         doReturn(false).when(mockLinkList).add("fuck");
//         mockLinkList.add("fuck");
//     }


//     //监控真实对象
//     @Test
//     public void Test11(){
//         List list = new ArrayList<>();
//         List spy = spy(list);

//         when(spy.size()).thenReturn(100);
//         spy.add("one");
//         spy.add("two");

//         //输出第一个元素
//         System.out.println(spy.get(0));

//         // 因为size()函数被打桩了,因此这里返回的是100
//         System.out.println(spy.size());

//         //验证交互
//         verify(spy).add("one");
//         verify(spy).add("two");

//         // 不可能 : 因为当调用spy.get(0)时会调用真实对象的get(0)函数,此时会发生IndexOutOfBoundsException异常，因为真实List对象是空的
//         when(spy.get(0)).thenReturn(100);
        
//         doReturn(1000).when(spy).get(0);// 你需要使用doReturn()来打桩
//         System.out.println(spy.get(0));
//     }

//     // 修改没有测试桩的调用的默认返回值（1.7版本之后）
//     @Test
//     public void Test12(){
//         //当你不需要函数调用打桩时你可以指定一个默认的答案。
//         List mock1 = mock(List.class,RETURNS_SMART_NULLS);
//         List mock2 = mock(List.class,new YourOwnAnswer);
//     }
    
//         @Mock
//     private List<String> mockList;

//     @Test
//     public void whenNotUseMockAnnotation_thenCorrect() {
//         List mockArriList = Mockito.mock(ArrayList.class);//创建的 mock 对象不会执行真实的方法实现

//         mockArriList.add("hello");//只是记录这个方法被真实调用过，不会真正添加元素
// //        Mockito.verify(mockArriList).add("hello"); 默认执行一次

//         verify(mockArriList, times(1)).add("hello");

//         Assertions.assertEquals(0,mockArriList.size()); //输出默认值为0是对的

//         Mockito.when(mockArriList.size()).thenReturn(100);
//         Assertions.assertEquals(100,mockArriList.size());
//     }

//     //用注解实现同上功能
//     @Test
//     public void whenUseMockAnnotation_thenMockIsInjected(){
//         mockList.add("hello");
//         verify(mockList).add("hello");
//         Assertions.assertEquals(0,mockList.size());
//         Mockito.when(mockList.size()).thenReturn(100);
//         Assertions.assertEquals(100,mockList.size());
//     }

//     //@Spy注解来监视一个现有实例
//     @Test
//     public void whenNotUseSpyAnnotation_thenCorrect(){
//         List<String> spyList = Mockito.spy(new ArrayList<String>());
//         spyList.add("one");//监视一个现有实例则真实添加元素不返回默认值
//         spyList.add("two");
//         System.out.println(spyList.size());
//         System.out.println(spyList);
//         verify(spyList).add("one");
//         verify(spyList).add("two");
//         Assertions.assertEquals(2,spyList.size());
//     }

//     @Spy
//     List<String> spiedList = Mockito.spy(new ArrayList<String>());

//     @Test
//     public void whenUseSpyAnnotation_thenCorrect(){
// //        我们使用了真实的spiedList.add()方法向被监视的列表中添加元素
//         spiedList.add("one");
//         spiedList.add("two");
//         verify(spiedList).add("one");
//         verify(spiedList).add("two");
//         Assertions.assertEquals(2,spiedList.size());

//         //先doreturn再when对象放在里面方法在外，而直接when时对象直接调用方法然后返回
//         Mockito.doReturn(100).when(spiedList).size();
//         Assertions.assertEquals(100,spiedList.size());
//     }

//     //不使用@Captor注解来创建ArgumentCaptor
//     @Test
//     public void whenNotUseCaptorAnnotation_thenCorrect(){
//         List mockList = Mockito.mock(List.class);
//         ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class);//用于捕获
//         mockList.add("one");
//         verify(mockList).add(arg.capture());//捕获动作行为
//         Assertions.assertEquals("one",arg.getValue());
//         System.out.println(arg.getValue());
//     }

//     //@Captor来实现相同的目的，创建ArgumentCaptor实例

//     @Mock
//     List list;

//     @Captor
//     ArgumentCaptor arg;

//     @Test
//     public void whenUseCaptorAnnotation_thenCorrect(){
//         list.add("one");
//         verify(list).add(arg.capture());
//         Assertions.assertEquals("one",arg.getValue());
//     }


//     @Mock
//     Map<String,String> wordMap;

//     @InjectMocks  //@InjectMocks将模拟的wordMap注入到MyDictionary实例dic中
//     MyDictionary dictionary;

//     @Test
//     public void whenUseInjectMocksAnnotation_thenCorrect(){
//         Mockito.when(wordMap.get("aword")).thenReturn("hello");
//         Assertions.assertEquals("hello",dictionary.getMeaning("aword")); //如果这样调用 注入mock是必须的
//     }

//     //将模拟对象注入到间谍对象中
//     //但是，Mockito 不支持将模拟对象注入到间谍对象中，以下测试会抛出异常：
//     @Spy
//     private MyDictionary spyDictionary;

//     @Test
//     public void whenUseInjectMocksAnnotation_thenCorrect(String input){
//         Mockito.when(wordMap.get("aword")).thenReturn("hello");
//         Assertions.assertEquals("hello",spyDic.getMeaning("aword"));
//     }

//     //如果我们想在间谍对象中使用模拟对象，可以通过构造函数手动注入
//     MyDictionary spyDic;

//     @BeforeEach
//     public void init(){
//         MockitoAnnotations.openMocks(this);
//         spyDic = Mockito.spy(new MyDictionary(wordMap));}


//     //当我们尝试使用用@Mock或@Spy注解的实例时，可能会遇到空指针异常
// //    @Test/*(expected = NullPointerException.class)*/
//     public void whenMockitoAnnotationsUninitialized_thenNPEThrown(){
//         Mockito.when(mockList.size()).thenReturn(2);
//     }

//     /*
//     * 注意事项
//         最后，关于 Mockito 注解有几点注意事项：

//         Mockito 的注解减少了重复的模拟对象创建代码。
//         它们使测试更具可读性。
//         @InjectMocks对于注入@Spy和@Mock实例都是必要的。
//     *
//     * */


//     //配置模拟对象的简单返回行为
//     @Test
//     public void myListTest(){
//         MyList myList = Mockito.mock(MyList.class);
//         Mockito.when(myList.add(anyString())).thenReturn(true);
//         boolean add = myList.add("hello");
//         Assertions.assertEquals(true,add);
//     }

//     //以另一种方式配置模拟对象的返回行为：
//     @Test
//     public void myListTest2(){
//         MyList myList = Mockito.mock(MyList.class);
//         Mockito.doReturn(true).when(myList).add(anyString());
//         boolean add = myList.add("hello");
//         Assertions.assertEquals(true,add);
//     }


//     //配置返回类型为 void 的方法抛出异常
//     @Test
//     public void myListTest3(){
//         MyList myList = Mockito.mock(MyList.class);
//         Mockito.doThrow(NullPointerException.class).when(myList).clear();
//         assertThrows(NullPointerException.class,()->myList.clear());
//     }

//     //配置多次调用的行为
//     @Test
//     public void myListTest4(){
//         MyList myList = Mockito.mock(MyList.class);
//         Mockito.when(myList.add(anyString()))
//                 .thenReturn(true)
//                 .thenThrow(IllegalArgumentException.class);

//         assertThrows(IllegalArgumentException.class,()->{
//             myList.add("hello");//第一次调用返回true第二次抛出，此后调用都抛出
//             myList.add("world");
//         });
//     }

//     //配置间谍对象的行为
//     @Test
//     public void myListTest5(){
//         MyList myList = new MyList();
//         MyList spy = Mockito.spy(myList);
//         Mockito.doThrow(NullPointerException.class).when(spy).size();

//         assertThrows(NullPointerException.class,()->spy.size());
//     }

//     //配置模拟对象调用真实的底层方法
//     @Test
//     public void myListTest6(){
//         MyList myList = Mockito.mock(MyList.class);
//         Mockito.when(myList.size()).thenCallRealMethod();
//         System.out.println(myList.size());
//         Assertions.assertEquals(1,myList.size());
//     }

//     //使用自定义 Answer 配置模拟方法调用
//     @Test
//     public void myListTest7(){
//         MyList myList = Mockito.mock(MyList.class);
//         Mockito.doAnswer(invocationOnMock -> "myself do answer").when(myList).get(Mockito.anyInt());
//         String elements = myList.get(1);
//         Assertions.assertEquals("myself do answer",elements);
//     }

//     @Test
//     public void myListTest8(){
//         //MyList类创建一个模拟对象，并将其命名为myMock
//         MyList myList = Mockito.mock(MyList.class, "myMock");

//         //然后我们将在模拟对象的一个方法上设置预期，并执行它
//         Mockito.when(myList.add(anyString())).thenReturn(false);
//         myList.add("hello");

//         //接下来，我们将在assertThatThrownBy中调用验证，并验证抛出的异常实例
//         //但是实际上，我们只调用了一次`add`方法，所以当使用`times(2)`去验证时，会抛出`TooFewActualInvocations`异常（实际调用次数太少）。
//         assertThatThrownBy(() -> verify(myList, times(2)).add(anyString())).isInstanceOf(TooFewActualInvocations.class);

//         //我们还可以验证异常消息，它应该包含关于模拟对象的信息
//         assertThatThrownBy(() -> verify(myList, times(2)).add(anyString())).isInstanceOf(TooFewActualInvocations.class).hasMessageContaining("myMock.add");

//     }

//     @Test
//     public void myListTest9(){
//         MyList myList = mock(MyList.class, new CustomAnswer());
//         boolean add = myList.add("hello");

//         verify(myList).add(anyString());
//         Assertions.assertEquals(false,add);
//     }

//     @Test
//     public void myListTest10(){
//         //MockSettings对象通过工厂方法实例化
//         MockSettings mockSettings = withSettings().defaultAnswer(new CustomAnswer());

//         //我们将使用该设置对象来创建一个新的模拟对象
//         MyList myList = mock(MyList.class, mockSettings);

//         boolean add = myList.add("hello");

//         verify(myList).add(anyString());
//         Assertions.assertEquals(false,add);
//     }


//     @Test
//     public void myListTest11(){
//         List<String> mockList = mock(MyList.class);
//         mockList.add("hello");
//         //验证对模拟对象的简单调用
//         verify(mockList).add(anyString());

//         //验证与模拟对象的交互次数
//         mockList.add("world");
//         verify(mockList,times(2)).add(anyString());

//         //验证没有与整个模拟对象发生交互
//         verifyNoInteractions(mockList);

//         //验证没有与特定方法发生交互
//         verify(mockList,times(0)).size();

//         //验证没有意外的交互（这应该会失败）
//         mockList.clear();
//         mockList.size();
//         verify(mockList).clear();
//         assertThrows(NoInteractionsWanted.class,()->verifyNoMoreInteractions(mockList));

//         //验证交互的顺序
//         mockList.clear();
//         mockList.add("hello");
//         mockList.size();
//         InOrder inOrder = inOrder(mockList);
//         inOrder.verify(mockList).clear();
//         inOrder.verify(mockList).add(anyString());
//         inOrder.verify(mockList).size();

//         //验证交互没有发
//         mockList.clear();
//         verify(mockList,never()).get(1);

//         //验证交互至少发生了特定次数
//         mockList.clear();
//         mockList.clear();
//         mockList.clear();
//         verify(mockList,atLeast(1)).clear();
//         verify(mockList,atMost(10)).clear();

//         //验证使用确切参数的交互
//         mockList.add("hello");
//         verify(mockList).add("hello");

//         //验证使用灵活 / 任意参数的交互
//         mockList.add("world");
//         verify(mockList).add(anyString());

//         //使用参数捕获验证交互
//         // 第一步模拟对象添加元素->第二步创建捕获对象关联->第三步验证模拟对象方法中添加逮捕->最后获取逮捕的value用assertThat断言value中是否包含添加的元素
//         List<String> myList = mock(MyList.class);
// //        myList.addAll(Lists.<String> new ArrayList("somethings")); 为了不报错暂注

//         ArgumentCaptor<List> arg = ArgumentCaptor.forClass(List.class);
//         verify(myList).addAll(arg.capture());
//         List value = arg.getValue();
//         assertThat(value).contains("somethings");
//     }

//     @Test
//     public void myListTest12(){
//         //只有当FlowerService的analyze方法接收到字符串 “poppy” 时，才会返回字符串 “Flower”。
//         doReturn("Flower").when(spyDictionary).getMeaning("flower");

//         //我们可以使用参数匹配器来配置模拟方法
//         // 由于anyString参数匹配器，无论我们向analyze方法传递什么值，结果都是相同的。参数匹配器允许我们进行灵活的验证或存根
//         when(spyDictionary.getMeaning(anyString())).thenReturn("Flower");

//         //不正确的事例
// //        when(flowerService.isABigFlower("poppy", anyInt())).thenReturn(true);
// //        assertThrows(InvalidUseOfMatchersException.class, () -> when(flowerService.isABigFlower("poppy", anyInt())).thenReturn(true));
//         //为了修复这个问题并如预期那样保留字符串名称 “poppy”，我们将使用eq匹配器
// //        when(flowerService.isABigFlower(eq("poppy"), anyInt())).thenReturn(true);


//         //成功例子
// //        when(flowerService.isABigFlower(eq("poppy"), anyInt())).thenReturn(true);
// //        Flower flower = new Flower("poppy", 15);
// //        Boolean response = flowerController.isABigFlower(flower);
// //        assertThat(response).isTrue();

//         //自定义参数匹配器
//         MessageDto messageDto = new MessageDto();
//         messageDto.setFrom("me");
//         messageDto.setTo("you");
//         messageDto.setText("hello");
// //        messageController.createMessage(messageDto);
// //        verify(messageService, times(1)).deliverMessage(any(Message.class));


//     }


//     @Test
//     public void myListTest13(){
//         //Mockito—— 使用间谍
//         //我们将监视一个现有的ArrayList对象
//         List<String> list = new ArrayList<String>();
//         List<String> spyList = spy(list);
//         spyList.add("hello");
//         spyList.add("world");
//         verify(spyList).add("hello");
//         verify(spyList).add("world");
//         assertThat(spyList).hasSize(2);
//         //实际的add()方法确实被调用了，并且spyList的大小变成了 2


//     }

//     //我们看看如何使用@Spy注解。我们可以使用@Spy注解来代替spy()方法
//     @Spy
//     List<String> spyList = new ArrayList<String>();

//     @Test
//     public void myListTest14(){
//         spyList.add("hello");
//         spyList.add("world");

//         verify(spyList).add("hello");
//         verify(spyList).add("world");

//         assertThat(spyList).hasSize(2);
//     }

//     //这里我们将使用doReturn()来覆盖size()方法

//     @Test
//     public void myListTest15(){
//         List<String> list = new ArrayList<>();
//         List<String> spyList = spy(list);

//         assertEquals(0,spyList.size());
//         doReturn(100).when(spyList).size(); //相当于调用了size方法
//         assertThat(spyList).hasSize(100);
//     }

//     //Mockito 中的 Mock 与 Spy

//     @Test
//     public void myListTest16(){
//         List mockedList = mock(ArrayList.class);
//         mockedList.add("one");
//         verify(mockedList).add("one");
//         assertThat(mockedList).hasSize(0);
//         //正如我们所看到的，向模拟列表中添加元素实际上并没有添加任何东西；它只是调用了该方法，没有其他副作用

//         //间谍的行为会有所不同；它实际上会调用add方法的真实实现，并将元素添加到底层列表中
//         ArrayList spy = spy(ArrayList.class);
//         spy.add("two");
//         verify(spy).add("two");
//         assertThat(spy).hasSize(1);

//         //当我们错误地使用模拟对象或间谍对象时，很可能会遇到这个常见异常
//         ArrayList<String> errorMock = new ArrayList<>();
//         doReturn("error").when(errorMock).size(); //这样相当于调用了size方法

//         //修复异常
//         ArrayList spy1 = spy(ArrayList.class);
//         assertThatNoException().isThrownBy(()->doReturn(100).when(spy1).size());
//     }

//     //我们使用@Mock注解来模拟DeliveryPlatform，它会被自动注入到我们的EmailService中（通过@InjectMocks注解）
//     //    @Mock
//     //    DeliveryPlatform platform;
//     //
//     //    @InjectMocks
//     //    EmailService emailService;
//     //    @Captor
//     //    ArgumentCaptor<Email> emailCaptor;

//    /* 用 Mockito 模拟 void 方法
//     doNothing()是 Mockito 对 void 方法的默认行为 */
//     @Test
//     public void myListTest17(){
//         MyList myList = mock(MyList.class);
//         doNothing().when(myList).add(isA(Integer.class), isA(String.class));
//         myList.add(0,"");
//         verify(myList).add(0, "");
//     }

//     //实现同上功能
//     @Test
//     public void whenAddCalled_thenVerified(){
//         List mock = mock(List.class);
//         mock.add(0,"");
//         verify(mock).add(0,"");
//     }

//     //doThrow()生成一个异常
//     @Test
//     public void whenRemoveCalled_thenVerified(){
//         MyList myList = mock(MyList.class);
//         doThrow().when(myList).add(isA(Integer.class), isNull());
//         assertThrows(Exception.class, () -> myList.add(0, null));
//     }

//     //我们像上面那样使用doNothing()，但要配合ArgumentCaptor
//     @Test
//     public void givenArgumentCaptor_whenAddCalled_thenValueCaptured(){
//         MyList myList = mock(MyList.class);
//         ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class);
//         doNothing().when(myList).add(isA(Integer.class),arg.capture());
//         myList.add(0,"fuckman");
//         assertEquals("fuckman",arg.getValue());
//     }


//     //我们可以使用 Mockito 的Answer来添加我们需要的行为
//     @Test
//     public void givenDoAnswer_whenAddCalled_thenAnswered(){
//         MyList myList = mock(MyList.class);
//         doAnswer(invocationOnMock -> {
//             Object arg0 = invocationOnMock.getArgument(0);
//             Object arg1 = invocationOnMock.getArgument(1);
//             assertEquals(3,arg0);
//             assertEquals("fuckman",arg1);
//             return null;
//         }).when(myList).add(any(Integer.class),any(String.class));
//         myList.add(0,"fuckman");
//     }

//     //部分模拟
//     @Test
//     public void givenDoCallRealMethod_whenAddCalled_thenRealMethodCalled(){

//         MyList myList = mock(MyList.class);
//         doCallRealMethod().when(myList).add(any(Integer.class),any(String.class));
//         myList.add(0,"fuckman");
//         verify(myList,times(1)).add(0,"fuckman");
//         //这样，我们可以调用实际的方法并同时验证它
//     }


//     @Test
//     public void givenStaticMethodWithNoArgs_whenMocked_thenReturnsMockSuccessfully(){
// //        assertThat(StaticUtils.name()).isEqualTo("Baeldung");

//         try (MockedStatic<StaticUtils> utilities = mockStatic(StaticUtils.class)){
//             utilities.when(StaticUtils::name).thenReturn("fukcman");
//             assertThat(StaticUtils.name()).isEqualTo("fukcman");//暂时改变
//         }

//         assertThat(StaticUtils.name()).isEqualTo("Baeldung");
//     }


//     @Test
//     public void givenStaticMethodWithArgs_whenMocked_thenReturnsMockSuccessfully(){
// //        assertThat(StaticUtils.range(2,6)).containsExactly(2,3,4,5);

// //        try (MockedStatic<StaticUtils> utilities = Mockito.mockStatic(StaticUtils.class)) {
// //            utilities.when(() -> StaticUtils.range(2, 6))
// //                    .thenReturn(Arrays.asList(10, 11, 12));
// //            assertThat(StaticUtils.range(2, 6)).containsExactly(10, 11, 12);
// //        }
//         assertThat(StaticUtils.range(2,6)).containsExactly(2,3,4,5);
//     }

// //    ....

// }

