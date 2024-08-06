# Quoridor Project
<p align="center">
  <img src="https://github.com/user-attachments/assets/c1d0b46e-8cf2-4478-b7c1-1c6cadbcff3a" width="500">
</p>

###### JAVA PROGRAMMING LAB (SWE2023)
###### INSTRUCTOR: Prof. TAMER ABUHMED
###### STUDENT NAME: Jungwoo Lee
###### STUDENT \#: 2020314556

## Introduction
이 게임은 보드게임 ‘쿼리도’에서 아이디어를 따왔다. 플레이어 수는 2명에서 4명까지 가능하다. 기본적인 게임 규칙은 다음과 같다. <br>
각각의 플레이어들은 하나의 말을 가지게 되며, 이를 자신이 시작한 위치에서 반대편 위치까지 옮기는 것이 목표가 된다. 상대방 플레이어들은 울타리를 설치함으로써 상대방의 경로를 방해할 수 있다.

<p align="center">
  <img src="https://github.com/user-attachments/assets/a7bb3d3a-fc8e-44cb-a368-50c610b8af0c" width="300">
</p>

이 때 반대편 위치라는 것은, P2와 P1의 관계처럼 정확히 반대인 위치를 나타내지는 않는다. P2의 입장에서 보면 P2는 그려진 네모 칸 내의 아무 위치에 도착하면 승리할 수 있다. 이는 다른 플레이어들에게도 모두 동일하게 적용된다. <br><br>
플레이어 중 한 명이라도 반대 위치에 도착하면 게임은 끝나게 된다. 즉 승자는 1명이다.

## Detailed Rules
1. **Setup** <br>
 보드의 크기는 7*7이며, 각 플레이어는 최대 4개의 울타리를 설치할 수 있다. 이 때 울타리는 가로 울타리, 세로 울타리의 두 종류로 나눌 수 있다.
2. **Movement** <br>
  게임이 시작되면, 다음과 같이 Current Player에서 현재 행동을 취할 수 있는 플레이어를 지시한다. 각 플레이어는 각 턴에서 두 가지의 행동을 취할 수 있다. 반드시 매 턴마다 두 버튼 중 하나를 누르고 movement를 진행해야 하며, 중간에 다른 movement로 바꿀 수 있다.
   1. Move Pawn
      * 플레이어는 자신의 상하좌우로 움직일 수 있으며, 대각선으로 움직이는 것은 허용되지 않는다.
      * 움직이려는 위치와 원래 위치 사이에 울타리가 설치되어 있으면 플레이어는 그 방향으로 움직일 수 없다.
   2. Place Fence
      * 플레이어는 상대방의 경로를 방해하기 위해 원하는 위치에 울타리를 설치할 수 있으며, 이미 다른 상대방이 설치한 위치에는 울타리를 설치할 수 없다.
      * 2개의 인접한 울타리를 선택해야 하며, 울타리의 종류는 반드시 같아야 한다.
         <p align="center">
          <img src="https://github.com/user-attachments/assets/f29ffae1-ca45-4918-b842-038c7b4953fc" width="300">
          </p>
      * 설치된 울타리의 색은 흰색이다.
      * 상대방의 목표 지점까지의 경로를 완전히 가로막게 울타리를 설치할 수 없다.
          <p align="center">
            <img src="https://github.com/user-attachments/assets/808252e5-b2b1-4fd4-998b-caaf4029a54c" width="300">
          </p>

## Menu Description
* Game Status <br>
  * 각 플레이어마다 설치할 수 있는 남은 울타리의 갯수를 보여준다.
* Program <br>
  * Rules: 규칙을 다시 확인할 수 있다.
  * Back to game start window: 게임 시작 화면으로 돌아간다.
  * Exit program: 프로그램을 종료한다.

