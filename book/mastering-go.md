# Go 마스터링하기 2장 - Go언어의 내부 살펴보기

## Go 컴파일러

```sh
go tool compile [option] file.go
```

### output file ?

| 파일명 | 확장자 | 명령 |
| --- | --- | --- |
| 오브젝트 파일 | *.o | go tool compile unsafe.go |
| 아카이브 파일 | *.a | go tool compile -pakc unsafe.go |


### 컴파일러란?

> 인간의 언어에 가까운 고급 언어로 작성된 원시 프로그램을 입력으로 받아 기계어(機械語)로 된 목적 프로그램을 출력하기 위해 사용되는 언어 번역 프로그램  
일반적 절차) 구문 분석 -> 최적화 -> 코드 생성 -> 링킹  
-[위키백과 - 컴파일러](https://ko.wikipedia.org/wiki/%EC%BB%B4%ED%8C%8C%EC%9D%BC%EB%9F%AC)

#### 컴파일러의 종류

| 종류 | 설명 | 사용예 |
| --- | --- | --- |
| native compiler | 컴파일러가 실행되는 환경(아키텍쳐/운영체제)과 object file 이 실행될 환경이 같은 경우 |
| cross compiler | 다른 컴퓨터나 운영체제에서도 실행되도록 실행파일 | gcc with cross compile options |
| byte code compiler | output file 이 가상 머신을 위해 제작된 바이트 코드 형태의 기계어가 됨 | JVM |


```sh
$ go tool compile --help
usage: compile [options] file.go...
  -% int
        debug non-static initializers
  -+    compiling runtime
  -B    disable bounds checking
  -C    disable printing of columns in error messages
  -D path
        set relative path for local imports
  -E    debug symbol export
  -G    accept generic code
  -I directory
        add directory to import search path
  -K    debug missing line numbers
  -L    show full file names in error messages
  -N    disable optimizations
  -S    print assembly listing
  -V    print version and exit
  -W    debug parse tree after type checking
  -asmhdr file
        write assembly header to file
  -bench file
        append benchmark times to file
  -blockprofile file
        write block profile to file
  -buildid id
        record id as the build id in the export metadata
  -c int
        concurrency during compilation (1 means no concurrency) (default 1)
  -clobberdead
        clobber dead stack slots (for debugging)
  -clobberdeadreg
        clobber dead registers (for debugging)
  -complete
        compiling complete package (no C or assembly)
  -cpuprofile file
        write cpu profile to file
  -d value
        enable debugging settings; try -d help
  -dwarf
        generate DWARF symbols (default true)
  -dwarfbasentries
        use base address selection entries in DWARF (default true)
  -dwarflocationlists
        add location lists to DWARF in optimized mode (default true)
  -dynlink
        support references to Go symbols defined in other shared libraries
  -e    no limit on number of errors reported
  -embedcfg file
        read go:embed configuration from file
  -gendwarfinl int
        generate DWARF inline info records (default 2)
  -goversion string
        required version of the runtime
  -h    halt on error
  -importcfg file
        read import configuration from file
  -importmap definition
        add definition of the form source=actual to import map
  -installsuffix suffix
        set pkg directory suffix
  -j    debug runtime-initialized variables
  -json string
        version,file for JSON compiler/optimizer detail output
  -l    disable inlining
  -lang string
        Go language version source code expects
  -linkobj file
        write linker-specific object to file
  -linkshared
        generate code that will be linked against Go shared libraries
  -live
        debug liveness analysis
  -m    print optimization decisions
  -memprofile file
        write memory profile to file
  -msan
        build code compatible with C/C++ memory sanitizer
  -mutexprofile file
        write mutex profile to file
  -nolocalimports
        reject local (relative) imports
  -o file
        write output to file
  -p path
        set expected package import path
  -pack
        write to file.a instead of file.o
  -r    debug generated wrappers
  -race
        enable race detector
  -shared
        generate code that can be linked into a shared library
  -smallframes
        reduce the size limit for stack allocated objects
  -spectre list
        enable spectre mitigations in list (all, index, ret)
  -std
        compiling standard library
  -symabis file
        read symbol ABIs from file
  -t    enable tracing for debugging the compiler
  -traceprofile file
        write an execution trace to file
  -trimpath prefix
        remove prefix from recorded source file paths
  -v    increase debug verbosity
  -w    debug type checking
  -wb
        enable write barrier (default true)

###########################
$ go tool compile -S test.go

"".main STEXT size=103 args=0x0 locals=0x40 funcid=0x0
        0x0000 00000 (test.go:5)        TEXT    "".main(SB), ABIInternal, $64-0
        0x0000 00000 (test.go:5)        CMPQ    SP, 16(R14)
        0x0004 00004 (test.go:5)        PCDATA  $0, $-2
        0x0004 00004 (test.go:5)        JLS     92
        0x0006 00006 (test.go:5)        PCDATA  $0, $-1
        0x0006 00006 (test.go:5)        SUBQ    $64, SP
        0x000a 00010 (test.go:5)        MOVQ    BP, 56(SP)
        0x000f 00015 (test.go:5)        LEAQ    56(SP), BP
        0x0014 00020 (test.go:5)        FUNCDATA        $0, gclocals·33cdeccccebe80329f1fdbee7f5874cb(SB)
        0x0014 00020 (test.go:5)        FUNCDATA        $1, gclocals·f207267fbf96a0178e8758c6e3e0ce28(SB)
        0x0014 00020 (test.go:5)        FUNCDATA        $2, "".main.stkobj(SB)
        0x0014 00020 (test.go:6)        MOVUPS  X15, ""..autotmp_9+40(SP)
        0x001a 00026 (test.go:6)        LEAQ    type.string(SB), DX
        0x0021 00033 (test.go:6)        MOVQ    DX, ""..autotmp_9+40(SP)
        0x0026 00038 (test.go:6)        LEAQ    ""..stmp_0(SB), DX
        0x002d 00045 (test.go:6)        MOVQ    DX, ""..autotmp_9+48(SP)
        0x0032 00050 (<unknown line number>)    NOP
        0x0032 00050 ($GOROOT\src\fmt\print.go:274)     MOVQ    os.Stdout(SB), BX
        0x0039 00057 ($GOROOT\src\fmt\print.go:274)     LEAQ    go.itab.*os.File,io.Writer(SB), AX
        0x0040 00064 ($GOROOT\src\fmt\print.go:274)     LEAQ    ""..autotmp_9+40(SP), CX
        0x0045 00069 ($GOROOT\src\fmt\print.go:274)     MOVL    $1, DI
        0x004a 00074 ($GOROOT\src\fmt\print.go:274)     MOVQ    DI, SI
        0x004d 00077 ($GOROOT\src\fmt\print.go:274)     PCDATA  $1, $0
        0x004d 00077 ($GOROOT\src\fmt\print.go:274)     CALL    fmt.Fprintln(SB)
        0x0052 00082 (test.go:7)        MOVQ    56(SP), BP
        0x0057 00087 (test.go:7)        ADDQ    $64, SP
        0x005b 00091 (test.go:7)        RET
        0x005c 00092 (test.go:7)        NOP
        0x005c 00092 (test.go:5)        PCDATA  $1, $-1
        0x005c 00092 (test.go:5)        PCDATA  $0, $-2
        0x005c 00092 (test.go:5)        NOP
        0x0060 00096 (test.go:5)        CALL    runtime.morestack_noctxt(SB)
        0x0065 00101 (test.go:5)        PCDATA  $0, $-1
        0x0065 00101 (test.go:5)        JMP     0
        0x0000 49 3b 66 10 76 56 48 83 ec 40 48 89 6c 24 38 48  I;f.vVH..@H.l$8H
        0x0010 8d 6c 24 38 44 0f 11 7c 24 28 48 8d 15 00 00 00  .l$8D..|$(H.....
        0x0020 00 48 89 54 24 28 48 8d 15 00 00 00 00 48 89 54  .H.T$(H......H.T
        0x0030 24 30 48 8b 1d 00 00 00 00 48 8d 05 00 00 00 00  $0H......H......
        0x0040 48 8d 4c 24 28 bf 01 00 00 00 48 89 fe e8 00 00  H.L$(.....H.....
        0x0050 00 00 48 8b 6c 24 38 48 83 c4 40 c3 0f 1f 40 00  ..H.l$8H..@...@.
        0x0060 e8 00 00 00 00 eb 99                             .......
        rel 2+0 t=24 type.string+0
        rel 2+0 t=24 type.*os.File+0
        rel 29+4 t=15 type.string+0
        rel 41+4 t=15 ""..stmp_0+0
        rel 53+4 t=15 os.Stdout+0
        rel 60+4 t=15 go.itab.*os.File,io.Writer+0
        rel 78+4 t=7 fmt.Fprintln+0
        rel 97+4 t=7 runtime.morestack_noctxt+0
os.(*File).close STEXT dupok size=86 args=0x8 locals=0x10 funcid=0x16
        0x0000 00000 (<autogenerated>:1)        TEXT    os.(*File).close(SB), DUPOK|WRAPPER|ABIInternal, $16-8
        0x0000 00000 (<autogenerated>:1)        CMPQ    SP, 16(R14)
        0x0004 00004 (<autogenerated>:1)        PCDATA  $0, $-2
        0x0004 00004 (<autogenerated>:1)        JLS     52
        0x0006 00006 (<autogenerated>:1)        PCDATA  $0, $-1
        0x0006 00006 (<autogenerated>:1)        SUBQ    $16, SP
        0x000a 00010 (<autogenerated>:1)        MOVQ    BP, 8(SP)
        0x000f 00015 (<autogenerated>:1)        LEAQ    8(SP), BP
        0x0014 00020 (<autogenerated>:1)        MOVQ    32(R14), R12
        0x0018 00024 (<autogenerated>:1)        TESTQ   R12, R12
        0x001b 00027 (<autogenerated>:1)        JNE     69
        0x001d 00029 (<autogenerated>:1)        NOP
        0x001d 00029 (<autogenerated>:1)        FUNCDATA        $0, gclocals·1a65e721a2ccc325b382662e7ffee780(SB)
        0x001d 00029 (<autogenerated>:1)        FUNCDATA        $1, gclocals·69c1753bd5f81501d95132d08af04464(SB)
        0x001d 00029 (<autogenerated>:1)        FUNCDATA        $5, os.(*File).close.arginfo1(SB)
        0x001d 00029 (<autogenerated>:1)        MOVQ    AX, ""..this+24(SP)
        0x0022 00034 (<autogenerated>:1)        MOVQ    (AX), AX
        0x0025 00037 (<autogenerated>:1)        PCDATA  $1, $1
        0x0025 00037 (<autogenerated>:1)        CALL    os.(*file).close(SB)
        0x002a 00042 (<autogenerated>:1)        MOVQ    8(SP), BP
        0x002f 00047 (<autogenerated>:1)        ADDQ    $16, SP
        0x0033 00051 (<autogenerated>:1)        RET
        0x0034 00052 (<autogenerated>:1)        NOP
        0x0034 00052 (<autogenerated>:1)        PCDATA  $1, $-1
        0x0034 00052 (<autogenerated>:1)        PCDATA  $0, $-2
        0x0034 00052 (<autogenerated>:1)        MOVQ    AX, 8(SP)
        0x0039 00057 (<autogenerated>:1)        CALL    runtime.morestack_noctxt(SB)
        0x003e 00062 (<autogenerated>:1)        MOVQ    8(SP), AX
        0x0043 00067 (<autogenerated>:1)        PCDATA  $0, $-1
        0x0043 00067 (<autogenerated>:1)        JMP     0
        0x0045 00069 (<autogenerated>:1)        LEAQ    24(SP), R13
        0x004a 00074 (<autogenerated>:1)        CMPQ    (R12), R13
        0x004e 00078 (<autogenerated>:1)        JNE     29
        0x0050 00080 (<autogenerated>:1)        MOVQ    SP, (R12)
        0x0054 00084 (<autogenerated>:1)        JMP     29
        0x0000 49 3b 66 10 76 2e 48 83 ec 10 48 89 6c 24 08 48  I;f.v.H...H.l$.H
        0x0010 8d 6c 24 08 4d 8b 66 20 4d 85 e4 75 28 48 89 44  .l$.M.f M..u(H.D
        0x0020 24 18 48 8b 00 e8 00 00 00 00 48 8b 6c 24 08 48  $.H.......H.l$.H
        0x0030 83 c4 10 c3 48 89 44 24 08 e8 00 00 00 00 48 8b  ....H.D$......H.
        0x0040 44 24 08 eb bb 4c 8d 6c 24 18 4d 39 2c 24 75 cd  D$...L.l$.M9,$u.
        0x0050 49 89 24 24 eb c7                                I.$$..
        rel 38+4 t=7 os.(*file).close+0
        rel 58+4 t=7 runtime.morestack_noctxt+0
os.(*File).isdir STEXT dupok size=86 args=0x8 locals=0x10 funcid=0x16
        0x0000 00000 (<autogenerated>:1)        TEXT    os.(*File).isdir(SB), DUPOK|WRAPPER|ABIInternal, $16-8
        0x0000 00000 (<autogenerated>:1)        CMPQ    SP, 16(R14)
        0x0004 00004 (<autogenerated>:1)        PCDATA  $0, $-2
        0x0004 00004 (<autogenerated>:1)        JLS     52
        0x0006 00006 (<autogenerated>:1)        PCDATA  $0, $-1
        0x0006 00006 (<autogenerated>:1)        SUBQ    $16, SP
        0x000a 00010 (<autogenerated>:1)        MOVQ    BP, 8(SP)
        0x000f 00015 (<autogenerated>:1)        LEAQ    8(SP), BP
        0x0014 00020 (<autogenerated>:1)        MOVQ    32(R14), R12
        0x0018 00024 (<autogenerated>:1)        TESTQ   R12, R12
        0x001b 00027 (<autogenerated>:1)        JNE     69
        0x001d 00029 (<autogenerated>:1)        NOP
        0x001d 00029 (<autogenerated>:1)        FUNCDATA        $0, gclocals·1a65e721a2ccc325b382662e7ffee780(SB)
        0x001d 00029 (<autogenerated>:1)        FUNCDATA        $1, gclocals·69c1753bd5f81501d95132d08af04464(SB)
        0x001d 00029 (<autogenerated>:1)        FUNCDATA        $5, os.(*File).isdir.arginfo1(SB)
        0x001d 00029 (<autogenerated>:1)        MOVQ    AX, ""..this+24(SP)
        0x0022 00034 (<autogenerated>:1)        MOVQ    (AX), AX
        0x0025 00037 (<autogenerated>:1)        PCDATA  $1, $1
        0x0025 00037 (<autogenerated>:1)        CALL    os.(*file).isdir(SB)
        0x002a 00042 (<autogenerated>:1)        MOVQ    8(SP), BP
        0x002f 00047 (<autogenerated>:1)        ADDQ    $16, SP
        0x0033 00051 (<autogenerated>:1)        RET
        0x0034 00052 (<autogenerated>:1)        NOP
        0x0034 00052 (<autogenerated>:1)        PCDATA  $1, $-1
        0x0034 00052 (<autogenerated>:1)        PCDATA  $0, $-2
        0x0034 00052 (<autogenerated>:1)        MOVQ    AX, 8(SP)
        0x0039 00057 (<autogenerated>:1)        CALL    runtime.morestack_noctxt(SB)
        0x003e 00062 (<autogenerated>:1)        MOVQ    8(SP), AX
        0x0043 00067 (<autogenerated>:1)        PCDATA  $0, $-1
        0x0043 00067 (<autogenerated>:1)        JMP     0
        0x0045 00069 (<autogenerated>:1)        LEAQ    24(SP), R13
        0x004a 00074 (<autogenerated>:1)        CMPQ    (R12), R13
        0x004e 00078 (<autogenerated>:1)        JNE     29
        0x0050 00080 (<autogenerated>:1)        MOVQ    SP, (R12)
        0x0054 00084 (<autogenerated>:1)        JMP     29
        0x0000 49 3b 66 10 76 2e 48 83 ec 10 48 89 6c 24 08 48  I;f.v.H...H.l$.H
        0x0010 8d 6c 24 08 4d 8b 66 20 4d 85 e4 75 28 48 89 44  .l$.M.f M..u(H.D
        0x0020 24 18 48 8b 00 e8 00 00 00 00 48 8b 6c 24 08 48  $.H.......H.l$.H
        0x0030 83 c4 10 c3 48 89 44 24 08 e8 00 00 00 00 48 8b  ....H.D$......H.
        0x0040 44 24 08 eb bb 4c 8d 6c 24 18 4d 39 2c 24 75 cd  D$...L.l$.M9,$u.
        0x0050 49 89 24 24 eb c7                                I.$$..
        rel 38+4 t=7 os.(*file).isdir+0
        rel 58+4 t=7 runtime.morestack_noctxt+0
go.cuinfo.packagename. SDWARFCUINFO dupok size=0
        0x0000 6d 61 69 6e                                      main
""..inittask SNOPTRDATA size=32
        0x0000 00 00 00 00 00 00 00 00 01 00 00 00 00 00 00 00  ................
        0x0010 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00  ................
        rel 24+8 t=1 fmt..inittask+0
go.info.fmt.Println$abstract SDWARFABSFCN dupok size=42
        0x0000 04 66 6d 74 2e 50 72 69 6e 74 6c 6e 00 01 01 11  .fmt.Println....
        0x0010 61 00 00 00 00 00 00 11 6e 00 01 00 00 00 00 11  a.......n.......
        0x0020 65 72 72 00 01 00 00 00 00 00                    err.......
        rel 0+0 t=23 type.[]interface {}+0
        rel 0+0 t=23 type.error+0
        rel 0+0 t=23 type.int+0
        rel 19+4 t=31 go.info.[]interface {}+0
        rel 27+4 t=31 go.info.int+0
        rel 37+4 t=31 go.info.error+0
go.string."Hello, World!" SRODATA dupok size=13
        0x0000 48 65 6c 6c 6f 2c 20 57 6f 72 6c 64 21           Hello, World!
""..stmp_0 SRODATA static size=16
        0x0000 00 00 00 00 00 00 00 00 0d 00 00 00 00 00 00 00  ................
        rel 0+8 t=1 go.string."Hello, World!"+0
runtime.nilinterequal·f SRODATA dupok size=8
        0x0000 00 00 00 00 00 00 00 00                          ........
        rel 0+8 t=1 runtime.nilinterequal+0
runtime.memequal64·f SRODATA dupok size=8
        0x0000 00 00 00 00 00 00 00 00                          ........
        rel 0+8 t=1 runtime.memequal64+0
runtime.gcbits.01 SRODATA dupok size=1
        0x0000 01                                               .
type..namedata.*interface {}- SRODATA dupok size=15
        0x0000 00 0d 2a 69 6e 74 65 72 66 61 63 65 20 7b 7d     ..*interface {}
type.*interface {} SRODATA dupok size=56
        0x0000 08 00 00 00 00 00 00 00 08 00 00 00 00 00 00 00  ................
        0x0010 4f 0f 96 9d 08 08 08 36 00 00 00 00 00 00 00 00  O......6........
        0x0020 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00  ................
        0x0030 00 00 00 00 00 00 00 00                          ........
        rel 24+8 t=1 runtime.memequal64·f+0
        rel 32+8 t=1 runtime.gcbits.01+0
        rel 40+4 t=5 type..namedata.*interface {}-+0
        rel 48+8 t=1 type.interface {}+0
runtime.gcbits.02 SRODATA dupok size=1
        0x0000 02                                               .
type.interface {} SRODATA dupok size=80
        0x0000 10 00 00 00 00 00 00 00 10 00 00 00 00 00 00 00  ................
        0x0010 e7 57 a0 18 02 08 08 14 00 00 00 00 00 00 00 00  .W..............
        0x0020 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00  ................
        0x0030 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00  ................
        0x0040 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00  ................
        rel 24+8 t=1 runtime.nilinterequal·f+0
        rel 32+8 t=1 runtime.gcbits.02+0
        rel 40+4 t=5 type..namedata.*interface {}-+0
        rel 44+4 t=-32763 type.*interface {}+0
        rel 56+8 t=1 type.interface {}+80
type..namedata.*[]interface {}- SRODATA dupok size=17
        0x0000 00 0f 2a 5b 5d 69 6e 74 65 72 66 61 63 65 20 7b  ..*[]interface {
        0x0010 7d                                               }
type.*[]interface {} SRODATA dupok size=56
        0x0000 08 00 00 00 00 00 00 00 08 00 00 00 00 00 00 00  ................
        0x0010 f3 04 9a e7 08 08 08 36 00 00 00 00 00 00 00 00  .......6........
        0x0020 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00  ................
        0x0030 00 00 00 00 00 00 00 00                          ........
        rel 24+8 t=1 runtime.memequal64·f+0
        rel 32+8 t=1 runtime.gcbits.01+0
        rel 40+4 t=5 type..namedata.*[]interface {}-+0
        rel 48+8 t=1 type.[]interface {}+0
type.[]interface {} SRODATA dupok size=56
        0x0000 18 00 00 00 00 00 00 00 08 00 00 00 00 00 00 00  ................
        0x0010 70 93 ea 2f 02 08 08 17 00 00 00 00 00 00 00 00  p../............
        0x0020 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00  ................
        0x0030 00 00 00 00 00 00 00 00                          ........
        rel 32+8 t=1 runtime.gcbits.01+0
        rel 40+4 t=5 type..namedata.*[]interface {}-+0
        rel 44+4 t=-32763 type.*[]interface {}+0
        rel 48+8 t=1 type.interface {}+0
go.itab.*os.File,io.Writer SRODATA dupok size=32
        0x0000 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00  ................
        0x0010 44 b5 f3 33 00 00 00 00 00 00 00 00 00 00 00 00  D..3............
        rel 0+8 t=1 type.io.Writer+0
        rel 8+8 t=1 type.*os.File+0
        rel 24+8 t=-32767 os.(*File).Write+0
type..importpath.fmt. SRODATA dupok size=5
        0x0000 00 03 66 6d 74                                   ..fmt
gclocals·33cdeccccebe80329f1fdbee7f5874cb SRODATA dupok size=8
        0x0000 01 00 00 00 00 00 00 00                          ........
gclocals·f207267fbf96a0178e8758c6e3e0ce28 SRODATA dupok size=9
        0x0000 01 00 00 00 02 00 00 00 00                       .........
"".main.stkobj SRODATA static size=32
        0x0000 01 00 00 00 00 00 00 00 f0 ff ff ff 10 00 00 00  ................
        0x0010 10 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00  ................
        rel 24+8 t=1 runtime.gcbits.02+0
gclocals·1a65e721a2ccc325b382662e7ffee780 SRODATA dupok size=10
        0x0000 02 00 00 00 01 00 00 00 01 00                    ..........
gclocals·69c1753bd5f81501d95132d08af04464 SRODATA dupok size=8
        0x0000 02 00 00 00 00 00 00 00                          ........
os.(*File).close.arginfo1 SRODATA static dupok size=3
        0x0000 00 08 ff                                         ...
os.(*File).isdir.arginfo1 SRODATA static dupok size=3
        0x0000 00 08 ff                                         ...
```

### go build ?

go build 의 내부적인 동작을 더 보고 싶을 경우, x 옵션 추가
```sh
$ go build -x defer.go
```

## 가비지 컬렉션

!채널변수에도 적용됨
더이상 사용하지 않는 메모리 공간을 해제하는 프로세스

- scope(유효 범위) 를 벗어난 오브젝트를 발견하고, 이를 더 참조할 일 이 없다고 판단하면 공간을 해제

#### gctrace debug option

```sh
$ GODEBUG=gctrace=1 go run garbage-collection.go
gc 1 @0.074s 0%: 0+1.2+0 ms clock, 0+0.61/0.61/0+0 ms cpu, 4->4->0 MB, 5 MB goal, 4 P
gc 2 @0.119s 1%: 1.0+1.1+0 ms clock, 4.2+0/0/0+0 ms cpu, 4->4->0 MB, 5 MB goal, 4 P
gc 3 @0.238s 0%: 0+0.58+0 ms clock, 0+0/0.58/1.7+0 ms cpu, 4->4->0 MB, 5 MB goal, 4 P
gc 4 @0.338s 0%: 0+0+0 ms clock, 0+0/0/0+0 ms cpu, 4->4->0 MB, 5 MB goal, 4 P
gc 5 @0.421s 0%: 0+0+0 ms clock, 0+0/0/0+0 ms cpu, 4->4->0 MB, 5 MB goal, 4 P
gc 6 @0.514s 0%: 0+1.9+0 ms clock, 0+0/0.99/0+0 ms cpu, 4->4->1 MB, 5 MB goal, 4 P
gc 7 @0.701s 0%: 0+0.56+0 ms clock, 0+0/0.50/0.50+0 ms cpu, 4->4->0 MB, 5 MB goal, 4 P
gc 8 @0.717s 0%: 0+1.0+0 ms clock, 0+0.50/0.50/0+0 ms cpu, 4->4->0 MB, 5 MB goal, 4 P     
gc 9 @0.747s 0%: 0+0.99+0 ms clock, 0+0/0.99/1.9+0 ms cpu, 4->4->0 MB, 5 MB goal, 4 P
gc 10 @0.772s 0%: 0+1.6+0 ms clock, 0+1.0/1.0/0.51+0 ms cpu, 4->4->0 MB, 5 MB goal, 4 P
gc 11 @0.800s 0%: 0+1.0+0 ms clock, 0+0/0/0+0 ms cpu, 4->4->0 MB, 5 MB goal, 4 P
# command-line-arguments
gc 1 @0.097s 0%: 0+2.7+0 ms clock, 0+0.65/1.5/3.2+0 ms cpu, 4->4->2 MB, 5 MB goal, 4 P    
gc 2 @0.102s 1%: 0+1.9+0 ms clock, 0+0.50/1.9/2.9+0 ms cpu, 5->6->4 MB, 6 MB goal, 4 P    
# command-line-arguments
gc 1 @0.016s 2%: 0+1.9+0 ms clock, 0+0/1.5/2.0+0 ms cpu, 4->6->6 MB, 5 MB goal, 4 P       
gc 2 @0.019s 4%: 0+2.5+0 ms clock, 0+0/2.5/0+0 ms cpu, 13->13->13 MB, 14 MB goal, 4 P     
gc 3 @0.036s 3%: 0+1.5+0 ms clock, 0+0/1.0/0.52+0 ms cpu, 24->25->24 MB, 27 MB goal, 4 P  
====
mem.Alloc:  114096
mem.TotalAlloc:  114096
mem.HeapAlloc:  114096
mem.NumGC:  0
====
gc 1 @0.015s 1%: 0+1.1+0 ms clock, 0+0.50/0.50/0.50+0 ms cpu, 286->286->0 MB, 287 MB goal, 4 P
gc 2 @0.160s 0%: 0+0+0 ms clock, 0+0/0/0+0 ms cpu, 286->286->0 MB, 287 MB goal, 4 P
gc 3 @0.191s 0%: 0+0+0 ms clock, 0+0/0/0+0 ms cpu, 286->286->0 MB, 287 MB goal, 4 P
gc 4 @0.229s 0%: 0+0+0 ms clock, 0+0/0/0+0 ms cpu, 286->286->0 MB, 287 MB goal, 4 P
gc 5 @0.264s 0%: 0+0+0 ms clock, 0+0/0/0+0 ms cpu, 286->286->0 MB, 287 MB goal, 4 P
gc 6 @0.298s 0%: 0+0+0 ms clock, 0+0/0/0+0 ms cpu, 286->286->0 MB, 287 MB goal, 4 P
gc 7 @0.347s 0%: 0+26+0 ms clock, 0+0/0/0+0 ms cpu, 286->286->0 MB, 287 MB goal, 4 P
gc 8 @0.458s 0%: 0+0+0 ms clock, 0+0/0/0+0 ms cpu, 286->286->0 MB, 287 MB goal, 4 P
gc 9 @0.497s 0%: 0+0.99+0 ms clock, 0+0.99/0/0+0 ms cpu, 286->286->0 MB, 287 MB goal, 4 P
====
mem.Alloc:  300111888
mem.TotalAlloc:  3000193040
mem.HeapAlloc:  300111888
mem.NumGC:  9
====
```

#### 힙을 비운다

(가비지 컬렉터가 실행될 시점의 힙 크기) -> (가비지 컬렉터가 실행을 마칠 시점의 힙 크기) -> (현재 힙 크기)

#### 동작 설명

1. 뮤테이터 스레드와 함께 동시에 실행
1. 여러 GC 스레드를 병렬로 실행 가능
1. type accurate. 타입을 엄격히 따짐
1. non-compacting. 비압축형 : 사용 중간 빈 힙 공간이 발생했을때, 가용 공간을 따로 묶어두지 않음.
1. non-generational. 비세대형 : 오래된 세대에서 신규 세대를 참고하는 경우 해당 참조를 별도로 기록하는 처리(write-barrier)가 필요함
1. 할당 작업에서 lock 이 발생하지 않으며, fragementation 을 최소화하도록 P 할당 영역마다 분리한 크기로 처리

[line engineering - Go 언어의 GC에 대해](https://engineering.linecorp.com/ko/blog/go-gc/)

### 삼색 알고리즘 (tricolor algorithm)

tricolor mark-and-sweep algorithm
write barrier

#### 구성

* 흰색 집합 오브젝트: 프로그램에서 더이상 접근할 수 없어서, 가비지 컬렉션의 대상
* 검은색 집합 오브젝트: 프로그램이 사용 중이며, 흰색 집합의 오브젝트를 가리키는 포인터가 확실히 없는 대상
* **회색 집합 오브젝트**: 프로그램이 현재 사용하고 있지만 흰색 오브젝트를 가리킬 수 있어서 검사 과정을 거쳐야할 대상

##### 색은 어떻게 판별하는가?

1. 모든 오브젝트는 흰색 상태로 시작
1. 모든 루트 오브젝트를 방문해 회색 상태로 변경  - 루트 오브젝트: 스택에 있는 오브젝트나 전역 변수 처럼, 애플리케이션에서 직접 접근할 수 있는 오브젝트
1. 가비지 컬렉터가 회색 오브젝트를 하나씩 뽑아, 해당 오브젝트가 흰색 집합에 있는 오브젝트를 가리키는 포인터를 가지는지 확인
  1. 포인터가 있으면, -> 포인터가 가르키는 흰색을 회색으로
  1. 포인터가 없으면, -> 검은색
1. 회색 오브젝트가 없어질때까지 반복
1. 회색 오브젝트가 없어지면 가비지 컬렉션이 되었다고 표현

!가비지 컬렉터의 mark-and-sweep algorithm 이 수행되는 동안은 프로그램 실행이 멈춰야함 -> 지연시간(latency) 발생

-> 지연시간 최소화를 위해 삼색 알고리즘 사용
-> 검은색 집합 오브젝트가 흰색 집합의 오브젝트를 가르키지 않아야함
-> write barrier. 유관한 오브젝트는 무조건 회색으로 변경

### unsafe 코드

Go 언어의 타입 안전성 및 메모리 보안 검사를 거치지 않는 코드. 포인터에 관련된 코드

!예기치 않은 값이 나올 수 있기 때문에, 꼭 필요한 경우에만 사용할 것

#### unsafe 패키지

C의 헤더처럼 들고 있음
go 컴파일러가 import 시 구현해줌

## go 언어에서 C 코드 호출하기

1. 안정성을 조금 희생하는 대신, 성능을 최대한 끌어내고 싶을 때 사용
1. 다른 언어와 대화하고 싶을 때
1. go 언어만으로는 구현하기 힘든 것을 만들고 싶을때

### 코드 직접 추가
```go
//#include <stdio.h>
//void callC() {
//     printf("inside C!\n");
// }
import "C"
import "fmt"

func main() {
    C.callC()
}
```

### C 파일 불러서 사용

cgo tool 로 전처리하도록 go build 에게 지시
```go
// #cgo CFLAGS: -I${SRCDIR}/callClib
// #cgo LDFLAGS: ${SRCDIR}/callC.a
// #include <stdlib.h>
// #include <callC.h>
import "C"
```

## C 코드에서 go 함수 호출하기

```go
// export PrintMessage
func PrintMessage() {
    fmt.Println("A Go func!")
}
```

```sh
$ go build -o usedByC.o -buildmode=c-shared usedByC.go
```

```c
#include <stdio.h>
#include "usedByC.h"

int main(int argc, char **argv) {
    printf("in C code!\n");
    PrintMessage();
}
```

## defer 키워드

defer 문을 담고 있는 함수가 리턴될 때까지 그 함수의 실행을 미룸
- 파일 입출력 연산

## panic 함수와 recover 함수

panic() : go 프로그램의 실행 흐름을 종료하고 패닉 상태로 변경
recover(): 패닉 상태에 빠졌던 go 루틴으로부터 제어권을 다시 가져옴

```go
package main

import "fmt"

func a() {
	fmt.Println("Inside a()!")
	defer func() {
		if c := recover(); c != nil {
			fmt.Println("Recover inside a()!")
		}
	}()
	fmt.Println("Call b()!")
	b()
	fmt.Println("b() exited!")
}

func b() {
	fmt.Println("Inside b()!")
	panic("Panic in b()!")
	fmt.Println("Exiting b()!")
}

func main() {
	a()
	fmt.Println("main() ended!")
}
```

```sh
# recover 에 대한 함수 실행되지 않음
$ go run panic-recover.go
Inside a()!
Call b()!
Inside b()!
Recover inside a()!
main() ended!
```

## 두 가지 유용한 리눅스 유틸리티

go 코드 내에서 잠재적인 병목 지점을 빨리 찾아보자
커맨드라인 프로그램 성능을 비교해보자

### strace

시스템 콜과 시그널의 실행 흐름을 추적
- 표준 에러 형태로 표시

```sh
$ strace find /usr 2>&1 | grep ioctl
```

### dtrace

수정하거나 다시 컴파일할 필요 없이 내부에서 일어나는 일을 시스템 차원에서 볼 수 있음

## Go 환경 파악하기

runtime package 에서 실행 환경에 대한 정보 제공.(go version, proc 수 등)
https://pkg.go.dev/runtime

## Go 어셈블러

https://go.dev/doc/asm

Go컴파일러에서 사용하는 어셈블리 언어를 볼 수 있음

```sh
$ GOOS=darwin GOARCH=amd64 go tool compile -S goEnv.go
```
* GOOS: 타겟 OS
* GOARCH: 컴파일 아키텍쳐

## 노드 트리

```sh
$ go tool compile -W test.go
```

## Go 코딩에 참고할만한 조언

* go 함수에서 발생한 에러를 로그에 기록하거나 리턴. 단, 특별한 이유가 없다면 둘다 하지 않음
* go 언어의 interface 는 데이터나 데이터의 구조가 아니라 동작을 정의
* io.Reader나 io.Writer 인터페이스를 사용하면 코드의 확장성을 높일수 있음
* 함수에 변수를 전달할때, 꼭 필요한 경우에만 포인터로 전달하며, 그 외의 경우 변수를 값으로 전달함
* 에러 변수는 string이 아닌 error 타입으로 지정
* 프로덕션 머신에서 go 코드를 테스트하지 않음
* 테스트한다
