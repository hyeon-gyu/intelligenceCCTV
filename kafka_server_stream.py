
import jetson.utils
import cv2
import numpy as np
from kafka import KafkaProducer
import time
# Kafka Producer 생성
producer = KafkaProducer(bootstrap_servers='192.168.0.38:9094')  # Kafka 서버 주
소

# 카메라 및 디스플레이 설정
camera = jetson.utils.videoSource("csi://1")      # '/dev/video0' for V4L2
display = jetson.utils.videoOutput("display://0") # 'my_video.mp4' for file

while display.IsStreaming():
    img = camera.Capture()

    # Convert the image from CUDA to NumPy format
    numpy_img = jetson.utils.cudaToNumpy(img)

    # Convert NumPy image to byte array
    _, buffer = cv2.imencode('.jpg', numpy_img)
    jpg_as_text = buffer.tobytes()

    # Send the image to Kafka
    producer.send('live', jpg_as_text)
    print("Frame sent to Kafka")
    time.sleep(0.1)
    # Display the frame
 #   display.Render(img)
 #   display.SetStatus("Camera Streaming | {:.0f} FPS".format(display.GetFrameRate()))

# 프로듀서 종료
producer.close()


